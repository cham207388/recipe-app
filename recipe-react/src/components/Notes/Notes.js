import React from 'react';
import classes from './Notes.css';

const notes = (props) => {
    return (

        <div>
            <div className={[classes.Center, classes.Notes].join(' ')}>
                <h4>{'Notes'}</h4>
                <p>{props.notes.recipeNotes}</p>
            </div>
            <table className={[classes.Center, classes.Table].join(' ')}>
                <thead>
                <tr>
                    <th>Prep Time</th>
                    <th>Cook Time</th>
                    <th>Difficulty</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>{props.prepTime} {'minutes'}</td>
                    <td>{props.cookTime} {'minutes'}</td>
                    <td>{props.difficulty}</td>
                </tr>
                </tbody>
            </table>
        </div>
    )
};

export default notes;