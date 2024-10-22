import React, {Component} from 'react';
import Auxiliary from "../../hoc/Auxiliary";
import mbahal from '../../assets/mbahal.jpg';
import Ingredients from "../../components/Ingredients/Ingredients";
import Notes from "../../components/Notes/Notes";
import Image from "../../components/Image/Image";
import Direction from "../../components/Direction/Direction";
import classes from './Recipe.css';

class Recipe extends Component {

    state = {
        recipes: [{
            recipeName: 'Mbahal',
            prepTime: 15,
            cookTime: 45,
            image: 'image',
            direction: 'bake canbeef, microwave ground p-nut, and and cook shrimp in rice',
            difficulty: 'EASY',
            ingredients: [
                {
                    name: 'pea-nuts',
                    description: 'pea nut powder',
                    amount: 2,
                    unitOfMeasure: {
                        uom: "ounces"
                    }
                },
                {
                    name: 'makarel',
                    description: 'canned fish',
                    amount: 2,
                    unitOfMeasure: {
                        uom: 'lb'
                    }
                },
                {
                    name: 'beef',
                    description: 'canned beef',
                    amount: 1,
                    unitOfMeasure: {
                        uom: 'lb'
                    }
                },
                {
                    name: 'shrimps',
                    description: 'cooked shrimps',
                    amount: 1,
                    unitOfMeasure: {
                        uom: 'lb'
                    }
                }],
            categories: [{
                categoryName: 'lunch'
            }, {
                categoryName: 'dinner'
            }],
            notes: {
                recipeNotes: 'Cooking mbahal can be divided into 4 steps: 1. microwave the ground peanut, 2. bake the makarel and the canned fish, 3. cooked the rice and shrimps, 4 mixed everything together.'
            }
        }]
    }

    render() {


        return (
            <div>
                <ul>
                    {this.state.recipes.map((recipe, index) => (
                        <Auxiliary>
                            <p className={classes.Recipe} key={index}>{recipe.recipeName}</p>
                            <Image image={mbahal} name='Mbahal'/>
                            <Ingredients ingredients={recipe.ingredients}/>
                            <Direction direction={recipe.direction}/>
                            <Notes notes={recipe.notes}
                                   prepTime={recipe.prepTime}
                                   cookTime={recipe.cookTime}
                                   difficulty={recipe.difficulty}/>
                        </Auxiliary>
                    ))}
                </ul>
            </div>
        );
    }
}

export default Recipe;