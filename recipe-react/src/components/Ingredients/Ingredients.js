import React from 'react';

const ingredient = (props) => {
    return(
        <div>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>UOM</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{props.name}</td>
                        <td>{props.description}</td>
                        <td>{props.uom}</td>
                        <td>{props.amount}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    )
};

export default ingredient;