import React, {useState} from 'react'

function Queries(){

  const [result, setResult] = useState([]);
  const [error, setError] = useState('');

  const runQuery = async (query) => {
    setResult([]);
    
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
      setResult(data);
      setError(null);
    } catch (err) {
      console.error("Query failed:", err);
      setError(`Query failed: ${err.message}`);
    } 
  };
  return (
    <div style={{ padding: '20px', maxWidth: '1000px' }}>
      <h1>Structured Queries</h1>
      <button style={{ padding: '10px', margin: '10px'}}
        onClick={() => runQuery("SELECT Teams.team_name AS team, AVG(Games.home_score) AS avg_score FROM Games JOIN Teams ON Teams.team_id = Games.home_team_id GROUP BY Games.home_team_id ORDER BY avg_score DESC LIMIT 5;")}>
          Top 5 teams with the highest average home-game scores
        </button>
      <button style={{ padding: '10px', margin: '10px'}} 
        onClick={() => runQuery("SELECT first_name, last_name FROM Coach WHERE role = 'Offensive Coordinator' AND first_name LIKE 'M%';")}>
        Offensive Coaches whose name starts with 'M'
        </button>
      <button style={{ padding: '10px', margin: '10px'}} 
        onClick={() => runQuery('SELECT Players.first_name, Players.last_name, PlayerStats.touchdowns FROM Players JOIN PlayerStats ON Players.player_id = PlayerStats.player_id ORDER BY PlayerStats.touchdowns DESC LIMIT 5 OFFSET 5;')}>
        Players ranked 5-10 on number of touchdowns
        </button>
      <button style={{ padding: '10px', margin: '10px'}} 
        onClick={() => runQuery('SELECT Coach.first_name, Coach.last_name, Teams.team_name, Coach.role FROM Coach LEFT JOIN Teams ON Coach.team_id = Teams.team_id UNION SELECT Coach.first_name, Coach.last_name, Teams.team_name, Coach.role FROM Coach RIGHT JOIN Teams ON Coach.team_id = Teams.team_id;')}>
        Teams and Coaches
        </button>
      <button style={{ padding: '10px', margin: '10px'}} 
        onClick={() => runQuery('SELECT p.first_name, p.last_name, s.passing_yards FROM Players p JOIN PlayerStats s ON p.player_id = s.player_id WHERE p.player_id IN (SELECT aw.player_id FROM PlayerAward aw) AND s.passing_yards > (SELECT AVG(passing_yards) FROM PlayerStats);')}>
          Players who have won at least one award and have passed for more yards than average
          </button>
      <button style={{ padding: '10px', margin: '10px'}} 
        onClick={() => runQuery("SELECT Players.first_name, Players.last_name, Award.award_name FROM Players JOIN PlayerAward on Players.player_id = PlayerAward.player_id JOIN Award ON PlayerAward.award_id = Award.award_id WHERE Award.award_name IN ('NFL MVP','Super Bowl MVP');")}>
          MVP and Super Bowl MVP list
          </button>
      
      {error && (
        <p style={{ color: 'red', marginTop: '20px' }}>{error}</p>
      )}

    {result.length > 0 && (
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
                  {Object.keys(result[0]).map((col) => (
                    <th key={col} style={{ padding: '8px' }}>{col}</th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {result.map((row, i) => (
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

export default Queries;

