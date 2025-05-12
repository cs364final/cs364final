import React from 'react';
import { Container } from '@mui/material';
import ResponsiveAppBar from './components/ResponsiveAppBar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Data from './Pages/Data';
import EditData from './Pages/Edit';
import SQL from './Pages/SQL';
import Querys from './Pages/Querys';

function App() {
  return (
    <>
    <ResponsiveAppBar></ResponsiveAppBar>
    <Container>
    <Routes>
        <Route path="/Data" element={<Data />} />
        <Route path="/Edit" element={<EditData />} />
        <Route path="/SQL" element={<SQL />} />
        <Route path="/Querys" element={<Querys />} />

      </Routes>
    </Container>
    </>
  );
}

export default App;
