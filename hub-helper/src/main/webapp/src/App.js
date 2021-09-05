import logo from './logo.svg';
import './App.css';
import { render } from 'react-dom';
import React, {Component} from 'react';
import MainPage from './components/MainPage'

class App extends Component{
  
  render() {
    return (
      <div>
        <MainPage />
      </div>
    );
  }
}

export default App;
