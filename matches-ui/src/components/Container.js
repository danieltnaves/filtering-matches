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

  constructor(props) {
    super(props);
    const {selectedUsers} = props
    this.state = {
      matches: [],
      refreshRequested: false,
      selectedUsers
    }
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.selectedUsers !== this.state.selectedUsers) {
      this.setState({ 
        ...this.state, 
        selectedUsers:  nextProps.selectedUsers 
      });
    } 
  }

  containerCallback = (dataFromChild) => {
    this.setState({ matches: dataFromChild , refreshRequested: false });
  }

  requestRefresh = () => {
    this.setState({...this.state, refreshRequested: true })
  }

  render () {
    const { classes } = this.props
    const matchListProps = {...this.state, requestRefresh: this.requestRefresh}
    return (
      <React.Fragment>
        <Grid container className={classes.root}>
            <Grid item xs={12}>
                <Grid container>
                    <FilterDetails 
                      callbackFromParent={this.containerCallback} 
                      refreshRequested={this.state.refreshRequested} 
                      currentPosition={this.props.currentPosition} />
                    <MatchList {...matchListProps} />
                </Grid>
            </Grid>
        </Grid>
      </React.Fragment>
    );
  }
}

Container.propTypes = {
  classes: PropTypes.object.isRequired,
  selectedUsers: PropTypes.object
};

export default withStyles(styles)(Container);