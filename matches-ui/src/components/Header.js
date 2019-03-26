import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import CssBaseline from '@material-ui/core/CssBaseline';
import Toolbar from '@material-ui/core/Toolbar';
import Chip from '@material-ui/core/Chip';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles'

const styles = {
    root: {
      flexGrow: 1,
    },
    grow: {
      flexGrow: 1,
    }
  };

class Header extends Component {
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
                <Chip
                    avatar={<Avatar>MS</Avatar>}
                    label="Maria - London"
                />
              </Toolbar>
            </AppBar>
          </div>
        );
    }
}

export default withStyles(styles)(Header);