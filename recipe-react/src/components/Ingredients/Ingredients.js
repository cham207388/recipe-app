import React from 'react';
import Ingredient from "./Ingredient/Ingredient";
import ingredient from "./Ingredient/Ingredient";

const ingredients = (props) => {

    return (
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
                {props.ingredients.forEach((ingredient, index) => (
                    <Ingredient key={index}
                                name={ingredient.name}
                                description={ingredient.description}
                                unitOfMeasure={ingredient.unitOfMeasure.uom}
                                amount={ingredient.amount}
                    />
                ))}
                </tbody>
            </table>
        </div>
    )
};

export default ingredients;