import React, { useState } from 'react';
import { FixedSizeList as List } from 'react-window';

const tables = ['players', 'teams', 'coach', 'games', 'playerStats', 'playerAwards'];
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
    const tablePath = selectedTable;
    let method = operation === 'Update' ? 'PUT' : operation === 'Delete' ? 'DELETE' : 'POST';
    let url = `${baseUrl}/${tablePath}/${operation.toLowerCase()}`;
    let body = null;

    if (operation === 'Read') {
      try {
        const res = await fetch(url);
        const data = await res.json();
        setResponse(data);
      } catch (err) {
        console.error("Fetch error:", err);
        setResponse(`Fetch error: ${err.message}`);
      }
      return;
    }

    if (operation === 'Delete') {
      if (selectedTable === 'playerAwards') {
        if (!formData.playerId || !formData.awardId) {
          setResponse("Error: playerId and awardId are required for PlayerAward delete.");
          return;
        }
        url += `?playerId=${formData.playerId}&awardId=${formData.awardId}`;
      } else if (selectedTable === 'players') {
        if (!formData.playerId) {
          setResponse("Error: playerId is required for DELETE");
          return;
        }
        url += `/${formData.playerId}`;
      } else {
        if (!formData.id) {
          setResponse("Error: id is required for DELETE");
          return;
        }
        url += `/${formData.id}`;
      }
    } else {
      if (operation === 'Update' && selectedTable === 'players' && !formData.playerId) {
        setResponse("Error: playerId is required for UPDATE");
        return;
      }
      body = JSON.stringify(formData);
    }

    try {
      const res = await fetch(url, {
        method,
        headers: { 'Content-Type': 'application/json' },
        body: method === 'POST' || method === 'PUT' ? body : undefined,
      });

      const result = await res.text();
      setResponse(result);
    } catch (err) {
      console.error("Fetch failed:", err);
      setResponse(`Fetch failed: ${err.message}`);
    }
  };

  const renderForm = () => {
    const fields = {
      players: ['playerId', 'firstName', 'lastName', 'nickname', 'position', 'birthDate', 'teamId'],
      teams: ['teamId', 'teamName', 'owner', 'record'],
      coach: ['coachId', 'firstName', 'lastName', 'role', 'teamId'],
      games: ['gameId', 'gameDate', 'homeTeamId', 'awayTeamId', 'homeScore', 'awayScore', 'stadium'],
      playerStats: ['statId', 'gameId', 'playerId', 'passingYards', 'rushingYards', 'receivingYards', 'touchdowns', 'tackles', 'sacks', 'interceptions'],
      playerAwards: ['playerId', 'awardId', 'year'],
    };

    const f = fields[selectedTable] || [];

    if (operation === 'Delete') {
      if (selectedTable === 'playerAwards') {
        return (
          <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', marginBottom: '12px' }}>
            <input
              placeholder="playerId"
              style={{ padding: '6px 8px', fontSize: '0.75rem', maxWidth: '320px' }}
              onChange={(e) => handleInputChange('playerId', e.target.value)}
            />
            <input
              placeholder="awardId"
              style={{ padding: '6px 8px', fontSize: '0.75rem', maxWidth: '320px' }}
              onChange={(e) => handleInputChange('awardId', e.target.value)}
            />
          </div>
        );
      }
      const key = selectedTable === 'players' ? 'playerId' : 'id';
      return (
        <input
          placeholder={key}
          style={{ padding: '6px 8px', fontSize: '0.75rem', maxWidth: '320px', marginBottom: '12px' }}
          onChange={(e) => handleInputChange(key, e.target.value)}
        />
      );
    }

    if (operation === 'Read') return null;

    return (
      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', marginBottom: '12px' }}>
        {f.map(field => (
          <div key={field} style={{ display: 'flex', flexDirection: 'column', maxWidth: '320px' }}>
            <label style={{ fontWeight: 'bold', fontSize: '0.75rem', marginBottom: '2px' }}>{field}</label>
            <input
              placeholder={field}
              style={{
                padding: '6px 8px',
                fontSize: '0.75rem',
                border: '1px solid #ccc',
                borderRadius: '4px',
                width: '100%',
              }}
              onChange={(e) => handleInputChange(field, e.target.value)}
            />
          </div>
        ))}
      </div>
    );
  };

  const renderResponseTable = () => {
    if (!Array.isArray(response) || response.length === 0) return <p>No data returned.</p>;
    const headers = Object.keys(response[0]);

    const Row = ({ index, style }) => {
      const row = response[index];
      return (
        <div style={{ ...style, display: 'flex', borderBottom: '1px solid #ccc', padding: '0 5px', fontSize: '0.75rem' }}>
          {headers.map((h, i) => (
            <div key={i} style={{ flex: 1, padding: '0 4px', whiteSpace: 'normal', wordBreak: 'break-word' }}>
              {row[h]}
            </div>
          ))}
        </div>
      );
    };

    return (
      <div>
        <div style={{ display: 'flex', fontWeight: 'bold', background: '#eee', padding: '5px', fontSize: '0.75rem' }}>
          {headers.map((h, i) => (
            <div key={i} style={{ flex: 1 }}>{h}</div>
          ))}
        </div>
        <List height={400} itemCount={response.length} itemSize={30} width="100%">
          {Row}
        </List>
      </div>
    );
  };

  return (
    <div style={{ padding: '20px', maxWidth: '1000px' }}>
      <h2 style={{ fontSize: '1.25rem' }}>CRUD Interface</h2>
      <div>
        <label>Table: </label>
        <select onChange={e => setSelectedTable(e.target.value)}>
          <option value="">Select Table</option>
          {tables.map(t => <option key={t} value={t}>{t.charAt(0).toUpperCase() + t.slice(1)}</option>)}
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
          {Array.isArray(response) ? renderResponseTable() : <pre>{response}</pre>}
        </div>
      )}
    </div>
  );
}

export default Edit;
