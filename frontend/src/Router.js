import React, { Component } from 'react';
import { Route } from 'react-router-dom';
import { Home, Auth, Join, Register } from './pages';



const Router = () => {
  return (
    <div>
      <Route exact path="/" component={Home}/>
      <Route exact path="/auth" component={Auth}/>
      <Route exact path="/join" component={Join}/>
      <Route exact path="/register" component={Register}/>
    </div>
  );
};

export default Router;