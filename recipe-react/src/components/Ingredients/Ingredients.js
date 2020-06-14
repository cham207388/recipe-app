import React, {Component} from 'react';
import Ingredient from "./Ingredient/Ingredient";
import classes from './Ingredients.css';

class Ingredients extends Component {

    render() {

        return (
            <div>
                <table className={[classes.Center, classes.Table].join(' ')}>
                    <thead>
                    <tr>
                        <th colSpan='4'>Ingredients</th>
                    </tr>
                    <tr>
                        <th>Name</th>
                        <th>Description</th>
                        <th>UOM</th>
                        <th>Amount</th>
                    </tr>
                    </thead>
                    <tbody>
                    {this.props.ingredients.map((ingredient, index) => (
                        <Ingredient key={index}
                                    name={ingredient.name}
                                    description={ingredient.description}
                                    unitOfMeasure={ingredient.unitOfMeasure}
                                    amount={ingredient.amount}
                        />
                    ))}
                    </tbody>
                </table>
            </div>
        )
    }
};

export default Ingredients;