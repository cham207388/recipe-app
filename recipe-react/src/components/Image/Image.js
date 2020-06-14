import React from 'react';
import classes from './Image.css';

const image = (props) => {
    return (
        <img className={classes.Image} src={props.image} alt={props.name}/>
    )
};

export default image;