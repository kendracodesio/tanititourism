import axios from 'axios';
import { useEffect, useState } from 'react';

interface DropdownProps {
    apiEndpoint: string;
    label: string;
    id: string;
    onChange?: (newValue: any) => void;

}

interface Item {
    id?: number;
    name?: string;  //value for region
    typeName?: string; //value for type
    cost?: string;
    label?:string; //for enum label

}

function AdminDropdown({ apiEndpoint, label, id, onChange }: DropdownProps) {
    const [items, setItems] = useState<Item[]>([]);

    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + apiEndpoint)
            .then(response => {
                setItems(response.data);
            });
    }, [apiEndpoint]);



    const handleSelectionChange = (newSelection: any) => {
        if (onChange) {
            onChange(newSelection);
        }
    };


    return (
        <div className="col-lg-4 mt-4 mb-4">
            <label htmlFor={id} className="form-label">{label}:</label>
            <select className="form-control" id={id} onChange={e => handleSelectionChange(e.target.value)}>
                {items.map((item: Item) => {
                    // For entities with IDs (region and type)
                    if (item.id) {
                        return <option value={item.id} key={item.id}>{item.name || item.typeName}</option>
                    }
                    // For enums with name-label pairs (cost)
                    else if (item.name && item.label) {
                        return <option value={item.name} key={item.name}>{`${item.name} - ${item.label}`}</option>
                    }
                })}
            </select>
        </div>
    )
}

export default AdminDropdown;