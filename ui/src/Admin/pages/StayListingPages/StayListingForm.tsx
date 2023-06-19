import React, {useEffect, useState} from 'react';
import {useParams} from "react-router-dom";
import axios from "axios";
import {Col, Row} from "react-bootstrap";
import Form from "react-bootstrap/Form";
import AdminFormDropdown from "../../components/AdminFormDropdown";
import Button from "react-bootstrap/Button";

interface Item {
    id: number; //for region
    name: string; //cost
    label: string; //cost
    typeName: string;

}

interface ListingFormData {
    name: string;
    description: string;
    phone: string;
    imageUrl: string;
    imageAltText: string;
    cost: any;
    region: { id: number; name: string; } | null;
    stayType: { id: number; typeName: string; } | null;
}

function StayListingForm() {
    let {id} = useParams();
    const [listingForm, setListingForm] = useState<ListingFormData | null>(null);
    const [selectedCost, setSelectedCost] = useState<string | null>(null);
    const [selectedRegion, setSelectedRegion] = useState<number | null>(null);
    const [selectedStayType, setSelectedStayType] = useState<number | null>(null);
    const [successMessage, setSuccessMessage] = useState<string | null>(null);
    const [errorMessage, setErrorMessage] = useState<string | null>(null);
    const [fieldErrors, setFieldErrors] = useState<any | null>(null);

    const apiURL = process.env.REACT_APP_API_URL;

    useEffect(() => {
        if (id != null) {
            axios.get(`${apiURL}/admin/places-to-stay/listing-detail/${id}`)
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
                stayType: {id: 0, typeName: ""}
            });
        }
    }, [apiURL, id])


    useEffect(() => {
        axios.get(`${apiURL}/admin/cost`)
            .then(response => {
                const allCosts = response.data;

                if (listingForm != null) {
                    const correspondingCost = allCosts.find((cost: {
                        label: string;
                    }) => cost.label === listingForm.cost);
                    if (correspondingCost) {
                        setSelectedCost(correspondingCost.name);
                        console.log('selectedCost:', correspondingCost.name);
                    }
                    if (listingForm.region) {
                        setSelectedRegion(listingForm.region.id);
                        console.log('selectedRegion:', listingForm.region.id);
                    }
                    if(listingForm.stayType){
                        setSelectedStayType(listingForm.stayType.id);
                        console.log('selectedStayType', listingForm.stayType.id)
                    }
                }

            });
    }, [listingForm, apiURL]);

    const handleSubmit = (event: React.FormEvent) => {
        event.preventDefault();
        if (!listingForm) return;

        const url = id ? `${apiURL}/admin/places-to-stay/update-listing/${id}` : `${apiURL}/admin/places-to-stay/new-listing`;
        const method = id ? 'put' : 'post';

        const payload = {
            name: listingForm.name,
            description: listingForm.description,
            phone: listingForm.phone,
            imageUrl: listingForm.imageUrl,
            imageAltText: listingForm.imageAltText,
            cost: selectedCost,
            regionId: selectedRegion,
            stayTypeId: selectedStayType
        };

        axios({
            method: method,
            url: url,
            data: payload
        })
            .then(response => {
                console.log(response.data);
                if (response.data.message) {
                    setSuccessMessage(response.data.message);
                    setErrorMessage(null);
                    setFieldErrors(null);
                } else if (response.data.error) {
                    setErrorMessage(response.data.error);
                    setSuccessMessage(null);
                }
            })
            .catch(error => {
                console.log(error);
                if (error.response && error.response.status === 400) {
                    const fieldErrors = error.response.data;
                    for (let field in fieldErrors) {
                        console.log(`Field: ${field}, Error: ${fieldErrors[field]}`);
                    }
                    setFieldErrors(fieldErrors);
                    if (fieldErrors) {
                        setErrorMessage("Please correct input errors below and resubmit");
                    } else {
                        setErrorMessage("An expected error occurred")
                    }
                    setSuccessMessage(null);
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

    const handleStayTypeChange = (newStayType: Item | null) => {
        if (newStayType) {
            setSelectedStayType(newStayType.id);
            if (listingForm != null && newStayType) {
                setListingForm({...listingForm, stayType: newStayType});
            }
        } else {
            setSelectedStayType(null);
            if (listingForm != null) {
                setListingForm({...listingForm, stayType: null});
            }
        }
    };

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
        <div className="container me-5 mt-3 admin-main-content">
            <Col className="text-center" xs={8} md={6} lg={4}>
                {successMessage && <div className="alert alert-success">{successMessage}</div>}
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
                        <AdminFormDropdown apiEndpoint="/admin/stay-type"
                                           label="Type"
                                           id="formStayType"
                                           onChange={handleStayTypeChange}
                                           selectedValue={selectedStayType}/>
                        {fieldErrors && fieldErrors.stayTypeId &&
                            <div className="alert alert-danger" role="alert">{fieldErrors.stayTypeId}</div>}
                        <AdminFormDropdown apiEndpoint="/admin/cost"
                                           label="Cost"
                                           id="formCost-stay"
                                           onChange={handleCostChange}
                                           selectedValue={selectedCost}/>
                        {fieldErrors && fieldErrors.cost &&
                            <div className="alert alert-danger" role="alert">{fieldErrors.cost}</div>}
                        <AdminFormDropdown apiEndpoint="/admin/region"
                                           label="Region"
                                           id="formRegion-stay"
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

export default StayListingForm;