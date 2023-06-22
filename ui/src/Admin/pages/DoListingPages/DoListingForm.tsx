import React, {useEffect, useState} from 'react';
import DoTypesCheckbox from "./components/DoTypesCheckbox";
import axios from "axios";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import AdminFormDropdown from "../../components/AdminFormDropdown";
import {useParams, useNavigate} from "react-router-dom";
import {Col, Row} from "react-bootstrap";


interface Item {
    id: number; //for region
    name: string;  //cost
    label: string; //cost

}

interface DoTypes {
    id: number;
    typeName: string;
}

interface ListingForm {
    name: string;
    description: string;
    phone: string;
    imageUrl: string;
    imageAltText: string;
    cost: string | null;
    region: { id: number | null; name: string; } | null;
    doTypes: DoTypes[] | null;
}

function DoListingForm() {
    let {id} = useParams();
    const [listingForm, setListingForm] = useState<ListingForm | null>(null);
    const [selectedCost, setSelectedCost] = useState<string | null>(null);
    const [selectedRegion, setSelectedRegion] = useState<number | null>(null);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const [fieldErrors, setFieldErrors] = useState<any | null>(null);
    const [costsValues, setCostsValues] = useState<{ name: string, label: string }[]>([]);
    const navigate = useNavigate();
    const apiURL = process.env.REACT_APP_API_URL;

    useEffect(() => {
        if (id != null) {
            axios.get(`${apiURL}/admin/api/things-to-do/listing-detail/${id}`)
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
                region: {id: null, name: ""},
                doTypes: []

            });
        }
    }, [apiURL, id])

    //get Cost enum names with corresponding label
    useEffect(() => {
        axios.get(`${apiURL}/admin/api/cost`)
            .then(response => {
                setCostsValues(response.data);
            });
    }, [apiURL]);

    useEffect(() => {
        //Match the label from listingId api call/listingForm to the corresponding name for selectedCost
        if (listingForm != null) {
            const correspondingCost = costsValues.find((cost: {
                label: string;
            }) => cost.label === listingForm.cost);
            if (correspondingCost) {
                setSelectedCost(correspondingCost.name);
                console.log('selectedCost:', correspondingCost.name);
            }
            //setting regionId from listingForm
            if (listingForm.region) {
                setSelectedRegion(listingForm.region.id);
                console.log('selectedRegion:', listingForm.region.id);
            }

        }
    }, [listingForm, apiURL, costsValues]);

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        setErrorMessage(null);
        setFieldErrors(null);
        if (!listingForm) return;

        const url = id ? `${apiURL}/admin/api/things-to-do/update-listing/${id}` : `${apiURL}/admin/api/things-to-do/new-listing`;
        const method = id ? 'put' : 'post';
        const successMessage = id ? "Listing updated successfully!" : "Listing created successfully!";

        const payload = {
            name: listingForm.name,
            description: listingForm.description,
            phone: listingForm.phone,
            imageUrl: listingForm.imageUrl,
            imageAltText: listingForm.imageAltText,
            cost: selectedCost,
            regionId: selectedRegion,
            doTypesIds: listingForm.doTypes?.map((doType: { id: number; }) => doType.id)
        };

        axios({
            method: method,
            url: url,
            data: payload,
            headers: {
                "X-Username": localStorage.getItem('username')
            }
        })
            .then(response => {
                if (response.data.id) {
                    navigate(`/admin/do-listings/listing-detail/${response.data.id}`, {state: {successMessage: successMessage}});
                }
                if (response.data.error) {
                    setErrorMessage(response.data.error);
                }
            })
            .catch(error => {
                console.log(error);
                if (error.response) {
                    if (error.response.status === 400) {
                        const fieldErrors = error.response.data;
                        for (let field in fieldErrors) {
                            console.log(`Field: ${field}, Error: ${fieldErrors[field]}`);
                        }
                        setFieldErrors(fieldErrors);
                        if (fieldErrors) {
                            setErrorMessage("Please correct input errors below and resubmit");
                        }
                    } else if (error.response.status === 500) {
                        setErrorMessage("Server error. Please try again later.");
                    }
                } else {
                    setErrorMessage("An unexpected error occurred")
                }
            })
    }

    const handleCostChange = (newCost: Item | null) => {
        if (newCost) {
            setSelectedCost(newCost.label);
            if (listingForm != null && newCost) {
                setListingForm({...listingForm, cost: newCost.label});
            }
        } else {
            setSelectedCost(null);
            if (listingForm != null) {
                setListingForm({...listingForm, cost: null});
            }
        }
    };

    const handleRegionChange = (newRegion: Item | null) => {
        if (newRegion) {
            setSelectedRegion(newRegion.id);
            if (listingForm != null && newRegion) {
                setListingForm({...listingForm, region: newRegion});
            }
        } else {
            setSelectedRegion(null);
            if (listingForm != null) {
                setListingForm({...listingForm, region: null});
            }
        }
    };

    const handleDoTypesChange = (newDoTypes: DoTypes[] | null) => {
        if (listingForm !== null) {
            const updatedForm = {...listingForm, doTypes: newDoTypes};
            setListingForm(updatedForm);
        }
    }

    const handleFieldChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const fieldName = event.target.name;
        const fieldValue = event.target.value;

        if (listingForm != null) {
            setListingForm({
                ...listingForm,
                [fieldName]: fieldValue,
            });
        }
    };

    return (
        <div className="container me-5 mt-3 admin-form-page">
            <Col className="text-center" xs={8} md={6} lg={4}>
                {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
            </Col>
            <Form onSubmit={handleSubmit}>
                <Row>
                    <Col xs={{span: 4}} className="ms-4">
                        <Form.Group className="mb-3 mt-4" controlId="formName">
                            <Form.Label>Listing Name</Form.Label>
                            <Form.Control type="text" placeholder="Enter name"
                                          name="name"
                                          value={listingForm ? listingForm.name : ''}
                                          onChange={handleFieldChange}
                            />
                            {fieldErrors && fieldErrors.name &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.name}</div>}
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formDescription">
                            <Form.Label>Description</Form.Label>
                            <Form.Control as="textarea" rows={7} placeholder="Enter description"
                                          name="description"
                                          value={listingForm ? listingForm.description : ''}
                                          onChange={handleFieldChange}
                            />
                            {fieldErrors && fieldErrors.description &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.description}</div>}
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formPhone">
                            <Form.Label>Phone</Form.Label>
                            <Form.Control type="tel" placeholder="Enter phone"
                                          name="phone"
                                          value={listingForm ? listingForm.phone : ''}
                                          onChange={handleFieldChange}
                            />
                            {fieldErrors && fieldErrors.phone &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.phone}</div>}
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formImageUrl">
                            <Form.Label>Image URL</Form.Label>
                            <Form.Control type="url" placeholder="Enter image URL"
                                          name="imageUrl"
                                          value={listingForm ? listingForm.imageUrl : ''}
                                          onChange={handleFieldChange}
                            />
                            {fieldErrors && fieldErrors.imageUrl &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.imageUrl}</div>}
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formImageAltText">
                            <Form.Label>Image Alt Text</Form.Label>
                            <Form.Control type="text" placeholder="Enter image alt text"
                                          name="imageAltText"
                                          value={listingForm ? listingForm.imageAltText : ''}
                                          onChange={handleFieldChange}
                            />
                            {fieldErrors && fieldErrors.imageAltText &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.imageAltText}</div>}
                        </Form.Group>
                    </Col>
                    <Col xs={{span: 4}} className="mt-4 ms-4">
                        <DoTypesCheckbox selectedDoTypes ={listingForm ? listingForm.doTypes : []}
                                         onChange={handleDoTypesChange}/>
                        {fieldErrors && fieldErrors.doTypesIds &&
                            <div className="alert alert-danger pe-5" role="alert">{fieldErrors.doTypesIds}</div>}
                        <div className="mt-3 mb-3">
                            <AdminFormDropdown apiEndpoint="/admin/api/cost"
                                               label="Cost"
                                               id="formCost"
                                               onChange={handleCostChange}
                                               selectedValue={selectedCost}/>
                            {fieldErrors && fieldErrors.cost &&
                                <div className="alert alert-danger" role="alert">{fieldErrors.cost}</div>}
                        </div>
                        <AdminFormDropdown apiEndpoint="/admin/api/region"
                                           label="Region"
                                           id="formRegion"
                                           onChange={handleRegionChange}
                                           selectedValue={selectedRegion}/>
                        {fieldErrors && fieldErrors.regionId &&
                            <div className="alert alert-danger" role="alert">{fieldErrors.regionId}</div>}
                    </Col>

                </Row>
                <Button className="mt-3 ms-4 submit-btn" variant="primary" type="submit">
                    Submit</Button>
            </Form>
        </div>
    );
}

export default DoListingForm;
