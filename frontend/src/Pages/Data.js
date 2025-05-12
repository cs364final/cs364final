import React, { useEffect, useState } from 'react';
import { FixedSizeList as List } from 'react-window';
import '../App.css';

function Data() {
  const [selectedTable, setSelectedTable] = useState('');
  const [data, setData] = useState([]);

  useEffect(() => {
    if (!selectedTable) return;
    fetch(`http://localhost:8080/${selectedTable}`)
      .then(res => res.json())
      .then(data => setData(data))
      .catch(err => console.error(err));
  }, [selectedTable]);

  const renderTable = () => {
    if (!Array.isArray(data) || data.length === 0) return <p>No data to display.</p>;

    const headers = Object.keys(data[0]);

    const Row = ({ index, style }) => {
      const row = data[index];
      return (
        <div
          style={{
            ...style,
            display: 'flex',
            borderBottom: '1px solid #ccc',
            padding: '0 5px',
            fontSize: '0.75rem',
            width: '100%',
          }}
        >
          {headers.map((h, i) => (
            <div
              key={i}
              style={{
                flex: 1,
                padding: '0 5px',
                overflowWrap: 'break-word',
                textAlign: 'left',
              }}
            >
              {row[h]}
            </div>
          ))}
        </div>
      );
    };

    return (
      <div style={{ height: '420px', overflowY: 'auto', width: '100%' }}>
        <div
          style={{
            display: 'flex',
            fontWeight: 'bold',
            background: '#eee',
            padding: '5px',
            fontSize: '0.75rem',
            borderBottom: '1px solid #ccc',
          }}
        >
          {headers.map((h, i) => (
            <div
              key={i}
              style={{
                flex: 1,
                padding: '0 5px',
                overflowWrap: 'break-word',
                textAlign: 'left',
              }}
            >
              {h}
            </div>
          ))}
        </div>
        <List height={380} itemCount={data.length} itemSize={30} width="100%">
          {Row}
        </List>
      </div>
    );
  };

  return (
    <div style={{ padding: '20px', maxWidth: '1000px' }}>
      <h2>Data Viewer</h2>
      <label>Select a table: </label>
      <select onChange={(e) => setSelectedTable(e.target.value)} defaultValue="">
        <option value="">-- Choose Table --</option>
        <option value="teams">Teams</option>
        <option value="coach">Coaches</option>
        <option value="games">Games</option>
        <option value="players">Players</option>
        <option value="playerStats">PlayerStats</option>
        <option value="playerAwards">PlayerAwards</option>
      </select>

      <div style={{ marginTop: '20px' }}>{renderTable()}</div>
    </div>
  );
}

export default Data;
