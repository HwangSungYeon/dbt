import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import Router from './Router';

const Root = ({store}) => {
    return (
        <BrowserRouter>
            <Route path="/" component={Router}/>
        </BrowserRouter>
    );
};

export default Root;