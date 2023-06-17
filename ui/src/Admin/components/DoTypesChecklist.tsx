import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Form from 'react-bootstrap/Form'

interface DoTypesProps {
    selectedDoTypes: any[];
    onChange: (newDoTypes: any[]) => void;
}

interface DoType {
    id: number;
    typeName: string;
}

function DoTypesChecklist({selectedDoTypes, onChange}: DoTypesProps) {
    const apiEndpoint = ("/admin/type")
    const [doTypes, setDoTypes] = useState<DoType[]>([]);

    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + apiEndpoint)
            .then(response => {
                setDoTypes(response.data);
            });

    }, []);

    const handleCheckboxChange = (doTypeId: number) => (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.checked) {
            onChange([...selectedDoTypes, doTypes.find(doType => doType.id === doTypeId)!]);
        } else {
            onChange(selectedDoTypes.filter(doType => doType.id !== doTypeId));
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
                checked={selectedDoTypes.some(selectedDoType => selectedDoType.id === doType.id)}
                onChange={handleCheckboxChange(doType.id)}
                />
            ))}
        </Form.Group>
    );
}

export default DoTypesChecklist;