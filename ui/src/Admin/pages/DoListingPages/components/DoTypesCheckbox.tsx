import React, {useEffect, useState} from 'react';
import Form from 'react-bootstrap/Form'
import axiosInstance from "../../../axiosInstance";

interface DoTypesProps {
    selectedDoTypes: DoTypes[] | null;
    onChange: (newDoTypes: DoTypes[] | null) => void;
}

interface DoTypes {
    id: number;
    typeName: string;
}

function DoTypesCheckbox({selectedDoTypes, onChange}: DoTypesProps) {
    const apiEndpoint = ("/admin/api/do-type")
    const [doTypes, setDoTypes] = useState<DoTypes[]>([]);
    const selectedDoTypesArray = selectedDoTypes || [];

    useEffect(() => {
        axiosInstance.get(process.env.REACT_APP_API_URL + apiEndpoint)
            .then(response => {
                setDoTypes(response.data);
            });

    }, []);

    const handleCheckboxChange = (doTypeId: number) => (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.checked) {
            onChange([...selectedDoTypesArray, doTypes.find(doType => doType.id === doTypeId)!]);
        } else {
            onChange(selectedDoTypesArray.filter(doType => doType.id !== doTypeId));
        }
    };

    return (
        <Form.Group>
            <Form.Label>Select Listing Type(s)</Form.Label>
            {doTypes.map((doType) => (
                <Form.Check
                key={doType.id}
                label={doType.typeName}
                type="checkbox"
                id={`checkbox-${doType.id}`}
                checked={selectedDoTypesArray.some(selectedDoType => selectedDoType.id === doType.id)}
                onChange={handleCheckboxChange(doType.id)}
                />
            ))}
        </Form.Group>
    );
}

export default DoTypesCheckbox;