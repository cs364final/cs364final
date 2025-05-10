import React, { useEffect, useState } from 'react';
import '../App.css';


function Data() {
  const [teams, setTeams] = useState([]);
  const [coaches, setCoaches] = useState([]);
  const [games, setGames] = useState([]);
  const [players, setPlayers] = useState([]);
  const [playerStats, setPlayerStats] = useState([]);
  const [playerAwards, setPlayerAwards] = useState([]);





  useEffect(() => {
    fetch("http://localhost:8080/teams")
      .then(res => res.json())
      .then(data => setTeams(data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/coach")
      .then(res => res.json())
      .then(data => setCoaches(data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/games")
      .then(res => res.json())
      .then(data => setGames(data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/players")
      .then(res => res.json())
      .then(data => setPlayers(data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/playerStats")
      .then(res => res.json())
      .then(data => setPlayerStats(data))
      .catch(err => console.error(err));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/playerAwards")
      .then(res => res.json())
      .then(data => setPlayerAwards(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <>
    <div>
      <h2>Teams</h2>
      <table>
        <tr>
          <th>Name</th>
          <th>Owner</th>
          <th>Record</th>
        </tr>
        {teams.map(team => (
          <tr key={team.teamId}>
            <td>{team.teamName}</td>  <td>{team.owner}</td> <td>{team.record}</td>
          </tr>
        ))}
      </table>
    </div>

    <div>
      <h2>Coaches</h2>
      <table>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Role</th>
          <th>Team</th>
        </tr>
        {coaches.map(coach => (
          <tr key={coach.coachId}>
            <td>{coach.firstName}</td><td>{coach.lastName}</td><td>{coach.role}</td><td>{coach.teamName}</td>
          </tr>
        ))}
      </table>
    </div>

    <div>
      <h2>Games</h2>
      <table>
        <tr>
          <th>Game ID</th>
          <th>Date</th>
          <th>Home Team</th>
          <th>Away Team</th>
          <th>Score(Home - Away)</th>
          <th>Stadium</th>
        </tr>
        {games.map(game => (
          <tr key={game.gameId}>
            <td>{game.gameId}</td><td>{game.gameDate}</td><td>{game.homeTeamName}</td><td>{game.awayTeamName}</td><td>{game.homeScore} - {game.awayScore}</td> <td>{game.stadium}</td>
          </tr>
        ))}
      </table>
    </div>

    <div>
      <h2>Players</h2>
      <table>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Nickname</th>
          <th>Position</th>
          <th>Birthdate</th>
          <th>Team</th>
        </tr>
        {players.map(player => (
          <tr key={player.playerId}>
            <td>{player.firstName}</td><td>{player.lastName}</td><td>{player.nickName}</td><td>{player.position}</td> <td>{player.birthDate}</td><td>{player.teamName}</td>
          </tr>
        ))}
      </table>
    </div>

    <div>
      <h2>Players Stats</h2>
      <table>
        <tr>
          <th>Name</th>
          <th>Passing Yards</th>
          <th>Rushing Yards</th>
          <th>Receiving Yards</th>
          <th>Touchdowns</th>

        </tr>
        {playerStats.map(playerStat => (
          <tr key={playerStat.statId}>
            <td>{playerStat.name}</td><td>{playerStat.passingYards}</td><td>{playerStat.rushingYards}</td><td>{playerStat.receivingYards}</td> <td>{playerStat.touchdowns}</td>
          </tr>
        ))}
      </table>
    </div>

    <div>
      <h2>Players Awards</h2>
      <table>
        <tr>
          <th>Name</th>
          <th>Award Name</th>
          <th>Year</th>
    
        </tr>
        {playerAwards.map(playerAward => (
          <tr>
            <td>{playerAward.name}</td><td>{playerAward.award}</td><td>{playerAward.year}</td>
          </tr>
        ))}
      </table>
    </div>



    </>
  );
}

export default Data;
