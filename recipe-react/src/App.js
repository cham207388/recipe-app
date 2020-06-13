import React, {Component} from 'react';
import './App.css';
import Recipe from "./containers/Recipe/Recipe";

class App extends Component {
    render() {
        return (
            <div className="App">
                <Recipe/>
            </div>
        );
    }
}

export default App;