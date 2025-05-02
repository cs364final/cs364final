import React, { useState } from 'react';

function SQL() {
  const [query, setQuery] = useState('');

  const handleSubmit = () => {
    console.log("Running query:", query);
    // Later: send this to the backend
  };

  const handleClear = () => {
    setQuery('');
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>SQL Query Runner</h2>
      <textarea
        rows="6"
        cols="60"
        placeholder="Write your SQL query here..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        style={{ fontSize: '16px', padding: '10px' }}
      />
      <br />
      <div style={{ marginTop: '10px' }}>
        <button
          onClick={handleSubmit}
          style={{
            padding: '10px 20px',
            fontSize: '16px',
            marginRight: '10px',
            cursor: 'pointer'
          }}
        >
          Run Query
        </button>

        <button
          onClick={handleClear}
          style={{
            padding: '10px 20px',
            fontSize: '16px',
            cursor: 'pointer',
            backgroundColor: '#f44336',
            color: 'white',
            border: 'none'
          }}
        >
          Clear
        </button>
      </div>
    </div>
  );
}

export default SQL;
