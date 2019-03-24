import React, { Component } from 'react';
import PropTypes from 'prop-types'
import classNames from 'classnames'
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
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

const API = globalVal.FILTERING_SERVICE_ENDPOINT;

class UserList extends Component {

  constructor() {
    super();
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
      .then(response => response.json())
      .then(data => this.setState({ matches: data.matches }));
  }

  handleChange = name => event => {
    this.setState(
      { [name]: event.target.checked }, 
      () => {
        console.log(this.state.hasPhoto)
        this.verifyFilters()
     })

  }

  verifyFilters = () => {

    var parameters = '';

    if (this.state.hasPhoto) {
      parameters += "&has_photo=true"
    }

    if (this.state.inContact) {
      parameters += "&in_contact=true"
    }

    if (this.state.favourite) {
      parameters += "&favourite=true"
    }

    if (this.state.compatibilityScore > 1) {
      parameters += "&compatibility_score=" + (this.state.compatibilityScore / 100);
    }

    if (this.state.age > 18) {
      parameters += "&age=" + this.state.age;
    }

    if (this.state.height > 135) {
      parameters += "&height=" + this.state.height;
    }

    if (this.state.distanceInKm > 30) {
      parameters += "&distance_in_km=" + this.state.distanceInKm + "&longitude=-0.118092&latitude=51.509865";
    }

    fetch(API + parameters)
      .then(response => response.json())
      .then(data => this.setState({ matches: data.matches }));
  }

  render () {

    const { classes } = this.props
    const { gilad, hasPhoto } = this.state;

    return (
      <React.Fragment>

  <Grid container className={classes.root}>
      <Grid item xs={12}>
          <Grid container>
            



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




              <Grid item xs={12} sm={9}>
                <div className={classNames(classes.layout, classes.cardGrid)}>
                  <Grid container spacing={40}>
                    {this.state.matches.map((match, index) => (
                      <Grid item key={index} xs={12} sm={6} md={4} lg={3}>
                        <Card className={classes.card}>
                          <CardMedia
                            className={classes.cardMedia}
                            image={match.mainPhoto}
                            title={match.displayName}
                          />
                          <CardContent className={classes.cardContent}>
                            <Typography gutterBottom variant="h6" component="h6">
                              {match.displayName}
                            </Typography>
                            <Typography>
                              Age: {match.age}
                            </Typography>
                            <Typography>
                              City Name: {match.cityName}
                            </Typography>
                            <Typography>
                              Compatibility Score: {match.compatibilityScore}
                            </Typography>
                            <Typography>
                              Contacts Exchanged: {match.contactsExchanged}
                            </Typography>
                            <Typography>
                              Favourite: {match.favourite ? 'yes' : 'no'}
                            </Typography>
                            <Typography>
                              Job Title: {match.jobTitle}
                            </Typography>
                            <Typography>
                              Religion: {match.religion}
                            </Typography>
                            <Typography>
                             Height In CM: {match.heightInCm}
                            </Typography>
                          </CardContent>
                        </Card>
                      </Grid>
                    ))}
                  </Grid>
                </div>
              </Grid>








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