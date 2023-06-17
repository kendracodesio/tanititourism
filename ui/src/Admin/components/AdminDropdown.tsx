import axios from 'axios';
import { useEffect, useState } from 'react';




interface DropdownProps {
    apiEndpoint: string;
    label: string;
    id: string;
    onChange?: (newValue: any) => void;
    selectedValue?: string | number;

}

interface Item {
    id?: number; // region
    name?: string;  //cost
    typeName?: string; //value for type
    cost?: string;
    label?:string; //for enum label

}

function AdminDropdown({ apiEndpoint, label, id, onChange, selectedValue }: DropdownProps) {
    const [items, setItems] = useState<Item[]>([]);


    useEffect(() => {
        axios.get(process.env.REACT_APP_API_URL + apiEndpoint)
            .then(response => {
                setItems(response.data);
            });
    }, [apiEndpoint]);


    const handleSelectionChange = (newSelection: any) => {
        if (onChange) {
            //For entities with IDs
            if (items[0] && 'id' in items[0]) {
                onChange(items.find((item) => item.id === parseInt(newSelection)));

            }
            //For enums with name and label
            else {
                onChange(items.find((item) => item.name === newSelection));

            }
        }
    };

    return (
        <div className="col-lg-4 mt-4 mb-4">
            <label htmlFor={id} className="form-label">{label}:</label>
            <select className="form-control" id={id} name={id}
                    value={selectedValue}
                    onChange={e => handleSelectionChange(e.target.value)}
            >
                {items.map((item: Item) => {
                    // For entities with IDs (region and type)
                    if (item.id) {
                        return <option value={item.id} key={item.id}>{item.name || item.typeName}</option>
                    }
                    // For enums with name-label pairs (cost)
                    else if (item.name && item.label) {
                        return <option value={item.name} key={item.name}>{item.name} - {item.label}</option>
                    }
                    // Default return value
                    else {
                        return null;
                    }
                })}
            </select>
        </div>
    );
}

export default AdminDropdown;