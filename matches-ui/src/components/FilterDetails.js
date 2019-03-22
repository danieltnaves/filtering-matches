import React, { Component } from 'react';
import Typography from '@material-ui/core/Typography';
import styled from 'styled-components'

const Wrapper = styled.div`
  background-color: #ccc; 
  max-width: 900; 
  margin: '0 auto'; 
  padding: 20px; 
`;

const Content = styled.div`
  max-width: 900; 
  margin: '0 auto'; 
  padding: 20px; 
`;

class FilterDetails extends Component {
  render () {
    return (
      <React.Fragment>
        <main>
          <Wrapper>
            <Content>
              <Typography variant="h6" align="center" color="textSecondary" paragraph>
              Something short and leading about the collection below—its contents.
              </Typography>
            </Content>
          </Wrapper>
        </main>
      </React.Fragment>
    );
  }
}

export default FilterDetails;