import React from 'react';
import Category from "./Category/Category";

const categories = (props) => {
    return (
        <div>
            <p>Categories</p>
            <ul>
                {props.categories.forEach((category, index) => {
                    <Category key={index}
                              categoryName={category.categoryName}/>
                })}
            </ul>
        </div>
    )
};

export default categories;