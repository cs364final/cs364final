import React, { useState } from 'react';

function SQL() {
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    setResults([]);

    try {
      const response = await fetch("http://localhost:8080/query", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ sql: query })
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(text);
      }

      const data = await response.json();
      setResults(data);
    } catch (err) {
      console.error("Query failed:", err);
      setError(`Query failed: ${err.message}`);
    } finally {
      setLoading(false);
    }
  };

  const handleClear = () => {
    setQuery('');
    setResults([]);
    setError('');
  };

  return (
    <div style={{ padding: '20px', fontFamily: 'Arial' }}>
      <h2>SQL Query Runner</h2>
      <textarea
        rows="6"
        cols="80"
        placeholder="Enter your SQL query here..."
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        style={{ fontSize: '16px', padding: '10px' }}
      />
      <br />
      <button
        onClick={handleSubmit}
        style={{
          marginTop: '10px',
          marginRight: '10px',
          padding: '10px 20px',
          fontSize: '16px',
          cursor: 'pointer'
        }}
        disabled={loading}
      >
        {loading ? 'Running...' : 'Run Query'}
      </button>

      <button
        onClick={handleClear}
        style={{
          marginTop: '10px',
          padding: '10px 20px',
          fontSize: '16px',
          cursor: 'pointer',
          backgroundColor: '#f44336',
          color: 'white',
          border: 'none'
        }}
        disabled={loading}
      >
        Clear
      </button>

      {error && (
        <p style={{ color: 'red', marginTop: '20px' }}>{error}</p>
      )}

      {results.length > 0 && (
        <table
          style={{
            marginTop: '20px',
            borderCollapse: 'collapse',
            width: '100%',
            textAlign: 'left'
          }}
          border="1"
        >
          <thead>
            <tr>
              {Object.keys(results[0]).map((col) => (
                <th key={col} style={{ padding: '8px' }}>{col}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {results.map((row, i) => (
              <tr key={i}>
                {Object.values(row).map((val, j) => (
                  <td key={j} style={{ padding: '8px' }}>{val}</td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default SQL;
