import React, { Component } from 'react';
import Header from './components/Header'
import Container from './components/Container'

class App extends Component {

  constructor() {
    super();
    this.state = {
      selectedUsers: { name: 'Maria London', long: '-0.118092', lat: '51.509865'},
      currentPosition: '&longitude=-0.118092&latitude=51.509865'
    }
  }

  headerCallback = (dataFromChild) => {
    console.log(dataFromChild)
    this.setState({ 
      currentPosition: dataFromChild
     });
  }


  render() {
    return (
      <div className="App">
        <React.Fragment>
          <Header callbackFromParent={this.headerCallback} />
          <Container {...this.state} />
        </React.Fragment>
      </div>
    );
  }
}

export default App;
