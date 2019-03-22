import React, { Component } from 'react';
import Header from './components/Header'
import FilterDetails from './components/FilterDetails.js'
import UserList from './components/UserList.js'

class App extends Component {
  render() {
    return (
      <div className="App">
        <React.Fragment>
          <Header />
          <FilterDetails />
          <UserList />
        </React.Fragment>
      </div>
    );
  }
}

export default App;
