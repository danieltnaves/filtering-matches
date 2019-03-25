import React, { Component } from 'react';
import PropTypes from 'prop-types'
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles'
import 'react-input-range/lib/css/index.css'
import FilterDetails from './FilterDetails'
import MatchList from './MatchList'

const styles = theme => ({
  layout: {
    width: 'auto',
    marginLeft: theme.spacing.unit * 3,
    marginRight: theme.spacing.unit * 3,
    [theme.breakpoints.up(1100 + theme.spacing.unit * 3 * 2)]: {
      width: 1100,
      marginLeft: 'auto',
      marginRight: 'auto',
    },
  },
  cardGrid: {
    padding: `${theme.spacing.unit * 8}px 0`,
  },
  card: {
    height: '100%',
    display: 'flex',
    flexDirection: 'column',
  },
  cardMedia: {
    paddingTop: '56.25%',
  },
  cardContent: {
    flexGrow: 1,
  },
  filterWrapper: {
    backgroundColor: theme.palette.background.paper,
  },
  filterContent: {
    maxWidth: 1100,
    margin: '0 auto'
  },
  root: {
    display: 'flex',
  },
  formControl: {
    margin: theme.spacing.unit * 3,
  },
  rangeText: {
    marginBottom: 30,
    marginTop: 15
  },
  rangeWrapper: {
    marginBottom: 30
  },

});

class UserList extends Component {

  constructor() {
    super();
    this.state = {
      matches: []
    }
  }

  myCallback = (dataFromChild) => {
    this.setState({ matches: dataFromChild });
  }

  render () {
    const { classes } = this.props
    return (
      <React.Fragment>
        <Grid container className={classes.root}>
            <Grid item xs={12}>
                <Grid container>
                    <FilterDetails callbackFromParent={this.myCallback} />
                    <MatchList {...this.state} />
                </Grid>
            </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

UserList.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(UserList);