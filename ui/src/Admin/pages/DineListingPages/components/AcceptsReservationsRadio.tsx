import React from 'react';
import Form from 'react-bootstrap/Form';

interface AcceptsReservationsProps {
    acceptsReservations: 'YES' | 'NO' | null;
    onChange: (newAcceptsReservations: 'YES' | 'NO' | null) => void;
}


function AcceptsReservationsRadio({acceptsReservations, onChange}: AcceptsReservationsProps) {

    const handleRadioChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        onChange(event.target.value as 'YES' | 'NO');
    };

    return (
        <Form.Group>
            <Form.Label>Accepts Reservations:</Form.Label>
            <Form.Check
                label="Yes"
                type="radio"
                name="acceptsReservations"
                value="YES"
                checked={acceptsReservations === 'YES'}
                onChange={handleRadioChange}
            />
            <Form.Check
                label="No"
                type="radio"
                name="acceptsReservations"
                value="NO"
                checked={acceptsReservations === 'NO'}
                onChange={handleRadioChange}
            />
        </Form.Group>

    );

}

export default AcceptsReservationsRadio;