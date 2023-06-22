
import {useEffect, useState} from 'react';
import axiosInstance from "../axiosInstance";


interface DropdownProps {
    apiEndpoint: string;
    label: string;
    id: string;
    onChange?: (newValue: any) => void;
    selectedValue?: string | number | null;

}

interface Item {
    id?: number; // region
    name?: string;  //cost
    typeName?: string; //value for type
    label?: string; //for enum label

}

function AdminFormDropdown({apiEndpoint, label, id, onChange, selectedValue}: DropdownProps) {
    const [items, setItems] = useState<Item[]>([]);


    useEffect(() => {
        axiosInstance.get(process.env.REACT_APP_API_URL + apiEndpoint)
            .then(response => {
                setItems(response.data);
            });
    }, [apiEndpoint]);


    const handleSelectionChange = (newSelection: any) => {
        if (onChange) {
            if (newSelection === "") {
                onChange(null);
            } else {
                //For entities with IDs
                if (items[0] && 'id' in items[0]) {
                    onChange(items.find((item) => item.id === parseInt(newSelection)));

                }
                //For enums with name and label
                else {
                    onChange(items.find((item) => item.name === newSelection));

                }
            }
        }
    };

    return (
        <div className="mt-4">
            <label htmlFor={id} className="form-label">{label}:</label>
            <select className="form-control" id={id} name={id}
                    value={selectedValue ?? undefined}
                    onChange={e => handleSelectionChange(e.target.value)}
            >
                <option value="">Select One</option>
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

export default AdminFormDropdown;