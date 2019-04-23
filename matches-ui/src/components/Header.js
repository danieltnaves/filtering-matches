import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Toolbar from '@material-ui/core/Toolbar';
import MenuItem from '@material-ui/core/MenuItem';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles'
import Select from '@material-ui/core/Select';
import InputLabel from '@material-ui/core/InputLabel';

const styles = {
    root: {
      flexGrow: 1,
    },
    grow: {
      flexGrow: 1,
    }
  };

  

class Header extends Component {

  constructor(props) {
    super(props);
    this.state = {
      user: 'Maria|&longitude=-0.118092&latitude=51.509865',
      selectedUsers: [
        {
          "cityName": "Leeds",
          "displayName": "Caroline",
          "long": -1.548567,
          "lat": 53.801277
        },
        {
          "cityName": "Solihull",
          "displayName": "Sharon",
          "long": -1.778197,
          "lat": 52.412811
        },
        {
          "cityName": "Cardiff",
          "displayName": "Natalia",
          "long": -3.17909,
          "lat": 51.481583
        },
        {
          "cityName": "Eastbourne",
          "displayName": "Marta",
          "long": 0.290472,
          "lat": 50.768036
        },
        {
          "cityName": "London",
          "displayName": "Maria",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "London",
          "displayName": "Stephanie",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "London",
          "displayName": "Claire",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "Swindon",
          "displayName": "Colette",
          "long": -1.772232,
          "lat": 51.568535
        },
        {
          "cityName": "Oxford",
          "displayName": "Caroline",
          "long": -1.257677,
          "lat": 51.752022
        },
        {
          "cityName": "Salisbury",
          "displayName": "Kate",
          "long": -1.794472,
          "lat": 51.068787
        },
        {
          "cityName": "Weymouth",
          "displayName": "Katie",
          "long": -2.457621,
          "lat": 50.614429
        },
        {
          "cityName": "Bournemouth",
          "displayName": "Clare",
          "long": -1.904755,
          "lat": 50.720806
        },
        {
          "cityName": "Plymouth",
          "displayName": "Laura",
          "long": -4.143841,
          "lat": 50.376289
        },
        {
          "cityName": "Inverness",
          "displayName": "Katlin",
          "long": -4.224721,
          "lat": 57.477772
        },
        {
          "cityName": "Aberdeen",
          "displayName": "Tracy",
          "long": -2.099075,
          "lat": 57.149651
        },
        {
          "cityName": "Ayr",
          "displayName": "Angie",
          "long": -4.629179,
          "lat": 55.458565
        },
        {
          "cityName": "Belfast",
          "displayName": "Samantha",
          "long": -5.926437,
          "lat": 54.607868
        },
        {
          "cityName": "Londonderry",
          "displayName": "Elizabeth",
          "long": -7.318268,
          "lat": 55.006763
        },
        {
          "cityName": "Leeds",
          "displayName": "Emma",
          "long": -1.548567,
          "lat": 53.801277
        },
        {
          "cityName": "London",
          "displayName": "Diana",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "London",
          "displayName": "Kysha",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "Swindon",
          "displayName": "Anne",
          "long": -1.772232,
          "lat": 51.568535
        },
        {
          "cityName": "Bournemouth",
          "displayName": "Daniela",
          "long": -1.904755,
          "lat": 50.720806
        },
        {
          "cityName": "London",
          "displayName": "Katherine",
          "long": -0.118092,
          "lat": 51.509865
        },
        {
          "cityName": "Harlow",
          "displayName": "Susan",
          "long": 0.10231,
          "lat": 51.772938
        }


      ],
    }
  }

  // handleChange = (distanceFilter) => {
  //   console.log(distanceFilter)
  // }

  handleChange = name => event => {
    this.setState({ [name]: event.target.value });
    this.props.callbackFromParent(event.target.value.split('|')[1])
  };


    render () {
        const { classes } = this.props
        return (
            <div className={classes.root}>
            <CssBaseline />
            <AppBar position="static">
              <Toolbar>
                <Typography variant="h5" color="inherit" className={classes.grow}>
                    Filtering Matches
                </Typography>
                <InputLabel htmlFor="age-native-simple">User: </InputLabel>
                <Select
                  value={this.state.user}
                  onChange={this.handleChange('user')}
                  inputProps={{
                    name: 'user',
                    id: 'user',
                  }}
                >
                  { (this.state.selectedUsers.map((user, index) => (
                    <MenuItem value={user.displayName +'|&longitude=' + user.long + '&latitude=' + user.lat}>{user.displayName} - {user.cityName}</MenuItem>
                  )))}
              </Select>
              </Toolbar>
            </AppBar>
          </div>
        );
    }
}

export default withStyles(styles)(Header);