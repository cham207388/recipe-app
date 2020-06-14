import React from 'react';
import classes from './Direction.css';

const direction = (props) => {
    return (
        <div className={classes.Direction}>
            <h4>{'Direction'}</h4>
            <p>{props.direction}</p>
        </div>
    )
};

export default direction;