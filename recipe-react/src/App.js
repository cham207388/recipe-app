import React, {Component} from 'react';
import classes from './App.css';
import Recipe from "./containers/Recipe/Recipe";


class App extends Component {
    render() {
        return (
            <div className={classes.App}>
                <Recipe/>
            </div>
        );
    }
}

export default App;
