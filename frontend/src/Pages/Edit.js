import React, { useState } from 'react';

const tables = ['Players', 'Teams', 'Coach', 'Games', 'PlayerStats', 'PlayerAward'];
const operations = ['Create', 'Read', 'Update', 'Delete'];

function Edit() {
  const [selectedTable, setSelectedTable] = useState('');
  const [operation, setOperation] = useState('');
  const [formData, setFormData] = useState({});
  const [response, setResponse] = useState(null);

  const handleInputChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }));
  };

  const handleSubmit = async () => {
    const baseUrl = 'http://localhost:8080/api/crud';
    const tablePath = selectedTable.toLowerCase();

    let method = 'POST';
    let url = `${baseUrl}/${tablePath}/${operation.toLowerCase()}`;
    let body = null;

    if (operation === 'Read') {
      const res = await fetch(url);
      const data = await res.json();
      setResponse(data);
      return;
    }

    if (operation === 'Delete') {
      if (selectedTable === 'PlayerAward') {
        url += `?playerId=${formData.playerId}&awardId=${formData.awardId}`;
      } else {
        url += `/${formData.id}`;
      }
    } else {
      body = JSON.stringify(formData);
    }

    const res = await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: method !== 'GET' && body,
    });

    const result = await res.text();
    setResponse(result);
  };

  const renderForm = () => {
    const fields = {
      Players: ['playerId', 'firstName', 'lastName', 'nickName', 'position', 'birthDate', 'teamId'],
      Teams: ['teamId', 'teamName', 'owner', 'record'],
      Coach: ['coachId', 'firstName', 'lastName', 'role', 'teamId'],
      Games: ['gameId', 'gameDate', 'homeTeamId', 'awayTeamId', 'homeScore', 'awayScore', 'stadium'],
      PlayerStats: ['statId', 'gameId', 'playerId', 'passingYards', 'rushingYards', 'receivingYards', 'touchdowns'],
      PlayerAward: ['playerId', 'awardId', 'year'],
    };

    const f = fields[selectedTable] || [];

    if (operation === 'Delete') {
      if (selectedTable === 'PlayerAward') {
        return (
          <>
            <input placeholder="playerId" onChange={(e) => handleInputChange('playerId', e.target.value)} />
            <input placeholder="awardId" onChange={(e) => handleInputChange('awardId', e.target.value)} />
          </>
        );
      } else {
        return <input placeholder="id" onChange={(e) => handleInputChange('id', e.target.value)} />;
      }
    }

    if (operation === 'Read') return null;

    return f.map(field => (
      <div key={field}>
        <input
          placeholder={field}
          onChange={(e) => handleInputChange(field, e.target.value)}
        />
      </div>
    ));
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>CRUD Interface</h2>
      <div>
        <label>Table: </label>
        <select onChange={e => setSelectedTable(e.target.value)}>
          <option value="">Select Table</option>
          {tables.map(t => <option key={t} value={t}>{t}</option>)}
        </select>
      </div>

      <div>
        <label>Operation: </label>
        <select onChange={e => setOperation(e.target.value)}>
          <option value="">Select Operation</option>
          {operations.map(op => <option key={op} value={op}>{op}</option>)}
        </select>
      </div>

      <div style={{ marginTop: '10px' }}>
        {selectedTable && operation && renderForm()}
      </div>

      {selectedTable && operation && (
        <button onClick={handleSubmit} style={{ marginTop: '10px' }}>Submit</button>
      )}

      {response && (
        <div style={{ marginTop: '20px' }}>
          <h4>Response:</h4>
          <pre>{typeof response === 'string' ? response : JSON.stringify(response, null, 2)}</pre>
        </div>
      )}
    </div>
  );
}

export default Edit;
