import React, { Component } from 'react';
import PropTypes from 'prop-types'
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles'

const styles = theme => ({
  heroUnit: {
    backgroundColor: theme.palette.background.paper,
  },
  heroContent: {
    maxWidth: 600,
    margin: '0 auto',
    padding: `${theme.spacing.unit * 8}px 0 ${theme.spacing.unit * 6}px`,
  },
  heroButtons: {
    marginTop: theme.spacing.unit * 4,
  }
});

class FilterDetails extends Component {
  render () {
    
    const { classes } = this.props

    return (
      <div className={classes.heroUnit}>
        <div className={classes.heroContent}>
          <Typography variant="h6" align="center" color="textSecondary" paragraph>
            Filter buttons here
          </Typography>
        </div>
      </div>
    );
  }
}

FilterDetails.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(FilterDetails);