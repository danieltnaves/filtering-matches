import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import CameraIcon from '@material-ui/icons/PhotoCamera';
import CssBaseline from '@material-ui/core/CssBaseline';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';


class Header extends Component {
    render () {
        return (
            <React.Fragment>
                <CssBaseline />
                <AppBar position="static">
                    <Toolbar>
                    <Typography variant="h6" color="inherit" noWrap>Filtering Matches</Typography>
                    </Toolbar>
                </AppBar>
            </React.Fragment>
        );
    }
}

export default Header;