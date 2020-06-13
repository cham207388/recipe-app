import React from 'react';

const ingredient = (props) => {
    return (
        <tr>
            <td>{props.name}</td>
            <td>{props.description}</td>
            <td>{props.unitOfMeasure.uom}</td>
            <td>{props.amount}</td>
        </tr>
    )
};

export default ingredient;