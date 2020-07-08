import React from 'react';
import { Route, BrowserRouter } from 'react-router-dom';
import Home from './pages/Home';
import Dashboard from './pages/Dashboard';
import Details from './pages/Details';
import partyMock from './utils/mocks/party-mock';

const Routes = () => {

  return(
    <BrowserRouter>
      <Route path="/" component={Home} exact={true} />
      <Route path="/dashboard" component={Dashboard} />
      <Route 
        path={`/details/:partyId`} 
        // component={Details}
        render={({ match }) => (
          <Details party={partyMock.find(party => party.id === match.params.partyId)} />
        )}
      />
    </BrowserRouter>
  )
}

export default Routes;