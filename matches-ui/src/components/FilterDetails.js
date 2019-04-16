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

  handleChange = name => event => {
    this.setState(
      { [name]: event.target.checked }, 
      () => {
        this.verifyFilters()
     })
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
    var parameters = ''
    // parameters += this.state.hasPhoto ? "&has_photo=true" : ""
    // parameters += this.state.inContact ? "&in_contact=true" : ""
    // parameters += this.state.favourite ? "&favourite=true" : ""
    // parameters += this.state.compatibilityScore > 1 ? "&compatibility_score=" + (this.state.compatibilityScore / 100) : ""
    // parameters += this.state.age > 18 ? "&age=" + this.state.age : ""
    // parameters += this.state.height > 135 ? "&height=" + this.state.height : ""
    // parameters += this.state.distanceInKm > 30 ? "&distance_in_km=" + this.state.distanceInKm + "&longitude=-0.118092&latitude=51.509865" : ""

    const ageFilter = new AgeFilter()
    const compatibilityScore = new CompatibilityScore()
    const distanceInKm = new DistanceInKm()
    const favouriteFilter = new FavouriteFilter()
    const hasPhoto = new HasPhoto();
    const heightFilter = new HeightFilter()
    const inContact = new InContact()

    var filtersArray = []
    filtersArray[0] = compatibilityScore;
    filtersArray[1] = distanceInKm;
    filtersArray[2] = favouriteFilter;
    filtersArray[3] = hasPhoto;
    filtersArray[4] = heightFilter;
    filtersArray[5] = inContact;
    filtersArray[6] = ageFilter;

    filtersArray.forEach((filter) => {
      parameters += filter.getQueryString(this.state.filters)
    })

    console.log(parameters)










    
    var currentFilterCount = parameters.split("=").length - 1
    parameters += "&page=" + (currentFilterCount !== this.state.filtersCount ? 0 : offset / this.state.limit)

    if (currentFilterCount !== this.state.filtersCount) {
      this.setState({ 
        offset : 0
      })
    }

    this.setState({ 
      filtersCount : currentFilterCount,
    })
    parameters += "&size=" + this.state.limit

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
                    <Checkbox id="hasPhoto" checked={this.state.filters['hasPhoto']} onChange={this.handleChange('hasPhoto')} />
                  }
                  label="Has Photo?"
                />
                <FormControlLabel
                  control={
                    <Checkbox id="inContact" checked={this.state.filters['inContact']} onChange={this.handleChange('inContact')} />
                  }
                  label="In Contact?"
                />
                <FormControlLabel
                  control={
                    <Checkbox id="favourite" checked={this.state.filters['favourite']} onChange={this.handleChange('favourite')} />
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
                        onChange={value => { 
                          const newFilters = this.state.filters
                          newFilters['compatibilityScore'] = value
                          this.setState({ filters: newFilters })}
                        }
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
                        onChange={value => {
                          const newFilters = this.state.filters;
                          newFilters['age'] = value;
                          this.setState({ filters: newFilters })
                        }}
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
                        onChange={value => {
                          const newFilters = this.state.filters;
                          newFilters['height'] = value;
                          this.setState({ filters: newFilters })}
                        }
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
                        onChange={value => {
                          const newFilters = this.state.filters;
                          newFilters['distanceInKm'] = value;
                          this.setState({ filters: newFilters })}
                        }
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