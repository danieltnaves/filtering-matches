import React, { Component } from 'react';
import PropTypes from 'prop-types'
import classNames from 'classnames'
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles'
import 'react-input-range/lib/css/index.css'
import Button from '@material-ui/core/Button';
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
  button: {
    margin: theme.spacing.unit,
  },
});



class MatchList extends Component {

  callSendSmile = (userId) => {
    const sendSmile = {
      userId
    };
    fetch(globalVal.SEND_SMILE_ENDPOINT, {
      method: 'PATCH',
      headers: {
        'Content-type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(sendSmile)
    })
    .then(response => {
      if (response.status === 200) {
        this.props.requestRefresh()
      }
    });
  }

  handleSendSmile = (userId) => {
    this.callSendSmile(userId)
  }

  render () {

    const { classes } = this.props

    return (
      <React.Fragment>
          <Grid item xs={12} sm={9}>
              <div className={classNames(classes.layout, classes.cardGrid)}>
                <Grid container spacing={40}>
                  {this.props.matches ? (this.props.matches.map((match, index) => (
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
                          <Button variant="contained" color="primary" className={classes.button} onClick={() => this.handleSendSmile(match.id)}>
                            Send Smile
                          </Button>
                        </CardContent>
                      </Card>
                    </Grid>
                  ))) : 'No matches found'}
                </Grid>
              </div>
            </Grid>
      </React.Fragment>
    );
  }
}

MatchList.propTypes = {
  classes: PropTypes.object.isRequired,
  requestRefresh: PropTypes.func 
};

export default withStyles(styles)(MatchList);