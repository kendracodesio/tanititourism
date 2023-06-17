import React, {useEffect, useState} from 'react';
import DoTypesChecklist from "./DoTypesChecklist";
import axios from "axios";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import AdminDropdown from "./AdminDropdown";
import {useParams} from "react-router-dom";
import {Col, Row} from "react-bootstrap";


interface Item {
    id: number; //for region
    name: string;  //cost
    label: string; //cost

}

interface DoListingFormData {
    name: string;
    description: string;
    phone: string;
    imageUrl: string;
    imageAltText: string;
    cost: any;
    region: { id: number; name: string; };
    doTypes: any[];
}

function DoListingForm() {
    let {id} = useParams();
    const [doListingForm, setListingForm] = useState<DoListingFormData | null>(null);
    const [selectedCost, setSelectedCost] = useState<string>('');
    const [selectedRegion, setSelectedRegion] = useState<number | undefined>(undefined);
    const apiURL = process.env.REACT_APP_API_URL;

    useEffect(() => {
        if (id != null) {
            axios.get(`${apiURL}/admin/things-to-do/listing-detail/${id}`)
                .then(response => {
                    console.log(response.data)
                    setListingForm(response.data);
                });
        } else {
            //If we don't have a listingId, we are creating a new listing. Initialize with default values.
            setListingForm({
                name: "",
                description: "",
                phone: "",
                imageUrl: "",
                imageAltText: "",
                cost: "",
                region: {id: 0, name: ""},
                doTypes: []

            });
        }
    }, [apiURL, id])

    useEffect(() => {
        axios.get(`${apiURL}/admin/cost`)
            .then(response => {
                const allCosts = response.data;

                if (doListingForm != null) {
                    const correspondingCost = allCosts.find((cost: {
                        label: string;
                    }) => cost.label === doListingForm.cost);
                    if (correspondingCost) {
                        setSelectedCost(correspondingCost.name);
                        console.log('selectedCost:', correspondingCost.name);
                    }
                    setSelectedRegion(doListingForm.region.id);

                    console.log('selectedRegion:', doListingForm.region.id);
                }
            });
    }, [doListingForm, apiURL]);

    const handleCostChange = (newCost: Item) => {
        setSelectedCost(newCost.name);
        if (doListingForm != null && newCost) {
            setListingForm({...doListingForm, cost: newCost.name});
        }
    };

    const handleRegionChange = (newRegion: Item) => {
        setSelectedRegion(newRegion.id);
        if (doListingForm != null && newRegion) {
            setListingForm({...doListingForm, region: newRegion});
        }
    };

    const handleDoTypesChange = (newDoTypes: any[]) => {
        if (doListingForm !== null) {
            const updatedForm = {...doListingForm, doTypes: newDoTypes};
            setListingForm(updatedForm);
        }
    };

    const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const fieldName = event.target.name;
        const fieldValue = event.target.value;

        if (doListingForm != null) {
            setListingForm({
                ...doListingForm,
                [fieldName]: fieldValue,
            });
        }
    };


    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (!doListingForm) return;

        const url = id ? `${apiURL}/admin/things-to-do/update-listing/${id}` : `${apiURL}/admin/things-to-do/new-listing`;
        const method = id ? 'put' : 'post';

        const payload = {
            name: doListingForm.name,
            description: doListingForm.description,
            phone: doListingForm.phone,
            imageUrl: doListingForm.imageUrl,
            imageAltText: doListingForm.imageAltText,
            cost: selectedCost,
            regionId: selectedRegion,
            doTypesIds: doListingForm.doTypes.map(doType => doType.id)
        };

        axios({
            method: method,
            url: url,
            data: payload
        })
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            });
    }



return (
    <div className="container p-5">
        <Form onSubmit={handleSubmit}>
            <Row>
                <Col>
                    <Form.Group controlId="formName">
                        <Form.Label>Listing Name</Form.Label>
                        <Form.Control type="text" placeholder="Enter name"
                                      name="name"
                                      value={doListingForm ? doListingForm.name : ''}
                                      onChange={handleFieldChange}/>
                    </Form.Group>

                    <Form.Group controlId="formDescription">
                        <Form.Label>Description</Form.Label>
                        <Form.Control as="textarea" placeholder="Enter description"
                                      name="description"
                                      value={doListingForm ? doListingForm.description : ''}
                                      onChange={handleFieldChange}/>
                    </Form.Group>

                    <Form.Group controlId="formPhone">
                        <Form.Label>Phone</Form.Label>
                        <Form.Control type="tel" placeholder="Enter phone"
                                      name="phone"
                                      value={doListingForm ? doListingForm.phone : ''}
                                      onChange={handleFieldChange}/>
                    </Form.Group>

                    <Form.Group controlId="formImageUrl">
                        <Form.Label>Image URL</Form.Label>
                        <Form.Control type="url" placeholder="Enter image URL"
                                      name="imageUrl"
                                      value={doListingForm ? doListingForm.imageUrl : ''}
                                      onChange={handleFieldChange}/>
                    </Form.Group>

                    <Form.Group controlId="formImageAltText">
                        <Form.Label>Image Alt Text</Form.Label>
                        <Form.Control type="text" placeholder="Enter image alt text"
                                      name="imageAltText"
                                      value={doListingForm ? doListingForm.imageAltText : ''}
                                      onChange={handleFieldChange}/>
                    </Form.Group>
                </Col>
                <Col>
                    <DoTypesChecklist selectedDoTypes={doListingForm ? doListingForm.doTypes : []}
                                      onChange={handleDoTypesChange}/>
                </Col>
            </Row>
            <div className="d-flex justify-content-start">
                <AdminDropdown apiEndpoint="/admin/cost"
                               label="Cost"
                               id="formCost"
                               onChange={handleCostChange}
                               selectedValue={selectedCost}/>

                <AdminDropdown apiEndpoint="/admin/region"
                               label="Region"
                               id="formRegion"
                               onChange={handleRegionChange}
                               selectedValue={selectedRegion}/>
            </div>
            <Button variant="primary" type="submit">
                Submit</Button>
        </Form>
    </div>
);
}

export default DoListingForm;
