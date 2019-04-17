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
import Pagination from "material-ui-flat-pagination";
import AgeFilter from '../filters/AgeFilter'
import CompatibilityScore from '../filters/CompatibilityScore'
import DistanceInKm from '../filters/DistanceInKm'
import FavouriteFilter from '../filters/FavouriteFilter'
import HasPhoto from '../filters/HasPhoto'
import HeightFilter from '../filters/HeightFilter'
import InContact from '../filters/InContact'

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
      matchesResult: [],
      filters : {
        'hasPhoto': false,
        'inContact' : false,
        'favourite' : false,
        'compatibilityScore' : 1,
        'age': 18,
        'height': 135,
        'distanceInKm' : 30,
      },
      offset: 0,
      limit: 8,
      filtersCount: 0
    }
  }
  
  componentDidMount() {
    this.callEndpoint(API + "&page=0&size=" + this.state.limit)
  }

  callEndpoint = (endpoint) => {
    fetch(endpoint)
      .then(response => {
        if (response.status === 200) {
          return response.json()
        }
        return DEFAULT_RESPONSE
      })
     .then(data => this.updateMatches(data))
  }

  verifyFilters = (offset) => {
    this.callEndpoint(API + this.verifyParameters(offset))
  }

  verifyParameters = (offset) => {
    var filtersArray = [
      new AgeFilter(),
      new CompatibilityScore(),
      new DistanceInKm(),
      new FavouriteFilter(),
      new HasPhoto(),
      new HeightFilter(),
      new InContact()
    ]
    
    var parameters = ''
    parameters = this.getFiltersParameters(filtersArray, this.state.filters)
    parameters = this.getPagingParameters(parameters, offset)
    console.log("Parameters: " + parameters)
    
    return parameters
  }

  getPagingParameters(parameters, offset) {
    var currentFilterCount = parameters.split("=").length - 1
    var differentCount = currentFilterCount !== this.state.filtersCount;

    parameters += "&page=" + (differentCount ? 0 : offset / this.state.limit)
    parameters += "&size=" + this.state.limit

    this.setState({ 
      filtersCount : currentFilterCount,
      offset : differentCount ? 0 : offset
    })

    return parameters;  
  }

  getFiltersParameters(filtersArray, filtersValues) {
    var parameters = ''
    filtersArray.forEach((filter) => {
      parameters += filter.getQueryString(filtersValues)
    })  
    return parameters   
  }

  updateMatches = (matches) => {
    this.setState({ matchesResult: matches });
    this.updateParentState(matches)
  }

  updateParentState = (matches) => {
    this.props.callbackFromParent(matches);
  }

  handleClick(offset) {
    this.setState({ offset: offset });
    this.verifyFilters(offset);
  }

  handleChange = name => event => {
    const newFilters = this.state.filters
    newFilters[name] = event.target.checked;
    this.setState({
      filters: newFilters
    }, () => {
      this.verifyFilters(0)      
    })
  }

  handleInputRangeChange(value, name) {
    const newFilters = this.state.filters
    newFilters[name] = value
    this.setState({ filters: newFilters })  
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
                    <Checkbox id="hasPhoto" name="hasPhoto" checked={this.state.filters['hasPhoto']} onChange={this.handleChange('hasPhoto')} />
                  }
                  label="Has Photo?" 
                />
                <FormControlLabel
                  control={
                    <Checkbox id="inContact" name="inContact" checked={this.state.filters['inContact']} onChange={this.handleChange('inContact')} />
                  }
                  label="In Contact?"
                />
                <FormControlLabel
                  control={
                    <Checkbox id="favourite" name="favourite" checked={this.state.filters['favourite']} onChange={this.handleChange('favourite')} />
                  }
                  label="Favourite?" 
                />
              </FormGroup>
              <FormGroup>
              <FormGroup>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Compatibility Score</Typography>
                  <InputRange
                        id="compatibilityScore"
                        maxValue={99}
                        minValue={1}
                        formatLabel={value => `${value} %`}
                        value={this.state.filters['compatibilityScore']}
                        onChange={value => { this.handleInputRangeChange(value, 'compatibilityScore')}}
                        onChangeComplete={value => this.verifyFilters(0)} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Age</Typography>
                  <InputRange
                        id="age"
                        maxValue={95}
                        minValue={18}
                        formatLabel={value => `${value} years`}
                        value={this.state.filters['age']}
                        onChange={value => { this.handleInputRangeChange(value, 'age')}}
                        onChangeComplete={value => this.verifyFilters(0)} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Height</Typography>
                  <InputRange
                        id="height"
                        maxValue={210}
                        minValue={135}
                        formatLabel={value => `${value} cm`}
                        value={this.state.filters['height']}
                        onChange={value => { this.handleInputRangeChange(value, 'height')}}
                        onChangeComplete={value => this.verifyFilters(0)} />
                </div>
                <div className={classes.rangeWrapper}>
                  <Typography variant="body1" className={classes.rangeText}>Distance in KM</Typography>
                  <InputRange
                        id="distanceInKm"
                        maxValue={300}
                        minValue={30}
                        formatLabel={value => `${value} KM`}
                        value={this.state.filters['distanceInKm']}
                        onChange={value => { this.handleInputRangeChange(value, 'distanceInKm')}}
                        onChangeComplete={value => this.verifyFilters(0)} />
                </div>
              </FormGroup>
              </FormGroup>
            </FormControl>
            {
              this.state && this.state.matchesResult.totalMatches > 0 &&
              <Pagination
                limit={this.state.limit}
                offset={this.state.offset}
                total={this.state.matchesResult.totalMatches}
                onClick={(e, offset) => this.handleClick(offset)}
              />
            }
            </Grid>
          </React.Fragment>
    );
  }
}

FilterDetails.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(FilterDetails);