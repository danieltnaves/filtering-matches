import React, { Component } from 'react';
import PropTypes from 'prop-types'
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles'
import FormLabel from '@material-ui/core/FormLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import InputRange from 'react-input-range';
import 'react-input-range/lib/css/index.css'
import globalVal from '../globalVar';

const styles = theme => ({
  filterWrapper: {
    backgroundColor: theme.palette.background.paper,
  },
  filterContent: {
    maxWidth: 1100,
    margin: '0 auto'
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

const API = globalVal.FILTERING_SERVICE_ENDPOINT;

const DEFAULT_RESPONSE = { matches : [], totalMatches: 0, totalMatchesPages: 0 };

class FilterDetails extends Component {

  constructor(props) {
    super(props);
    this.state = {
      matches: [],
      hasPhoto: false,
      inContact: false,
      favourite: false,
      compatibilityScore: 1,
      age: 18,
      height: 135,
      distanceInKm: 30
    }
  }
  
  componentDidMount() {
    fetch(API)
      .then(response => { 
        if (response.status === 200) {
          return response.json()
        }
        if (response.status === 204) {
          return DEFAULT_RESPONSE;
        }
      })
      .then(data => { 
        this.updateMatches(data.matches)
      });
  }

  handleChange = name => event => {
    this.setState(
      { [name]: event.target.checked }, 
      () => {
        this.verifyFilters()
     })
  }

  verifyFilters = () => {
    var parameters = this.verifyParameters();
    fetch(API + parameters)
      .then(response => {
        if (response.status === 200) {
          return response.json()
        }
        if (response.status === 204) {
          return DEFAULT_RESPONSE;
        }
      })
     .then(data => this.updateMatches(data.matches));
  }

  verifyParameters = () => {
    var parameters = '';
    parameters += this.state.hasPhoto ? "&has_photo=true" : "";
    parameters += this.state.inContact ? "&in_contact=true" : "";
    parameters += this.state.favourite ? "&favourite=true" : "";
    parameters += this.state.compatibilityScore > 1 ? "&compatibility_score=" + (this.state.compatibilityScore / 100) : "";
    parameters += this.state.age > 18 ? "&age=" + this.state.age : "";
    parameters += this.state.height > 135 ? "&height=" + this.state.height : "";
    parameters += this.state.distanceInKm > 30 ? "&distance_in_km=" + this.state.distanceInKm + "&longitude=-0.118092&latitude=51.509865" : "";
    return parameters;
  }

  updateMatches = (matches) => {
    this.updateParentState(matches)
  }

  updateParentState = (matches) => {
    this.props.callbackFromParent(matches);
  }

  render () {

    const { classes } = this.props

    return (
      <React.Fragment>
        <Grid item xs={12} sm={3}>
            <FormControl component="fieldset" className={classes.formControl}>
              <FormLabel component="legend">Filtering details</FormLabel>
              <FormGroup row>
                <FormControlLabel
                  control={
                    <Checkbox checked={this.state.hasPhoto} onChange={this.handleChange('hasPhoto')} />
                  }
                  label="Has Photo?"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={this.state.inContact} onChange={this.handleChange('inContact')} />
                  }
                  label="In Contact?"
                />
                <FormControlLabel
                  control={
                    <Checkbox checked={this.state.favourite} onChange={this.handleChange('favourite')} />
                  }
                  label="Favourite?"
                />
              </FormGroup>
              <FormGroup>
              <FormGroup>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Compatibility Score</Typography>
                  <InputRange
                        maxValue={99}
                        minValue={1}
                        formatLabel={value => `${value} %`}
                        value={this.state.compatibilityScore}
                        onChange={value => this.setState({ compatibilityScore: value })}
                        onChangeComplete={value => this.verifyFilters()} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Age</Typography>
                  <InputRange
                        maxValue={95}
                        minValue={18}
                        formatLabel={value => `${value} years`}
                        value={this.state.age}
                        onChange={value => this.setState({ age: value })}
                        onChangeComplete={value => this.verifyFilters()} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Height</Typography>
                  <InputRange
                        maxValue={210}
                        minValue={135}
                        formatLabel={value => `${value} cm`}
                        value={this.state.height}
                        onChange={value => this.setState({ height: value })}
                        onChangeComplete={value => this.verifyFilters()} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Distance in KM</Typography>
                  <InputRange
                        maxValue={300}
                        minValue={30}
                        formatLabel={value => `${value} KM`}
                        value={this.state.distanceInKm}
                        onChange={value => this.setState({ distanceInKm: value })}
                        onChangeComplete={value => this.verifyFilters()} />
                </div>
              </FormGroup>
              </FormGroup>
            </FormControl>
            </Grid>
          </React.Fragment>
    );
  }
}

FilterDetails.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(FilterDetails);