import React, { Component } from 'react';
import PropTypes from 'prop-types'
import Grid from '@material-ui/core/Grid';
import { withStyles } from '@material-ui/core/styles'
import 'react-input-range/lib/css/index.css'
import FilterDetails from './FilterDetails'
import MatchList from './MatchList'

const styles = theme => ({
  root: {
    display: 'flex'
  }
});

class Container extends Component {

  constructor() {
    super();
    this.state = {
      matches: []
    }
  }

  containerCallback = (dataFromChild) => {
    this.setState({ matches: dataFromChild });
  }

  render () {
    const { classes } = this.props
    return (
      <React.Fragment>
        <Grid container className={classes.root}>
            <Grid item xs={12}>
                <Grid container>
                    <FilterDetails callbackFromParent={this.containerCallback} />
                    <MatchList {...this.state} />
                </Grid>
            </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

Container.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Container);