import React, { Component } from 'react';
import Header from './components/Header'
import Container from './components/Container'

class App extends Component {
  render() {
    return (
      <div className="App">
        <React.Fragment>
          <Header />
          <Container />
        </React.Fragment>
      </div>
    );
  }
}

export default App;
