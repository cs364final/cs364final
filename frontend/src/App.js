import React from 'react';
import { Container } from '@mui/material';
import ResponsiveAppBar from './components/ResponsiveAppBar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Data from './Pages/Data';
import EditData from './Pages/Edit';
import SQL from './Pages/SQL';
import Queries from './Pages/Queries';
import { Navigate } from 'react-router-dom';

function App() {
  return (
    <>
    <ResponsiveAppBar></ResponsiveAppBar>
    <Container>
    <Routes>
        <Route index element={<Navigate to="/Data" replace />} />
        <Route path="/Data" element={<Data />} />
        <Route path="/Edit" element={<EditData />} />
        <Route path="/SQL" element={<SQL />} />
        <Route path="/Queries" element={<Queries/>} />
      </Routes>
    </Container>
    </>
  );
}

export default App;
