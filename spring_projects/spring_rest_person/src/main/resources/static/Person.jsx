import React, { useState, useEffect } from 'react';
import axios from 'axios';

const PersonTable = ({ persons, onEdit, onDelete }) => (
  <table border="1">
    <thead>
      <tr>
        <th>ID</th>
        <th>名前</th>
        <th>年齢</th>
        <th>性別</th>
        <th>編集</th>
        <th>削除</th>
      </tr>
    </thead>
    <tbody>
      {persons.map((person) => (
        <tr key={person.personId}>
          <td>{person.personId}</td>
          <td>{person.personName}</td>
          <td>{person.age}</td>
          <td>{person.gender === 'male' ? '男性' : person.gender === 'female' ? '女性' : ''}</td>
          <td><button onClick={() => onEdit(person)}>編集</button></td>
          <td><button onClick={() => onDelete(person.personId)}>削除</button></td>
        </tr>
      ))}
    </tbody>
  </table>
);

const App = () => {
  const [persons, setPersons] = useState([]);
  const [editingPerson, setEditingPerson] = useState({ personId: '', personName: '', age: '', gender: '' });

  useEffect(() => {
    axios.get("http://localhost:8080/persons")
      .then(response => setPersons(response.data))
      .catch(error => console.error("Error loading persons:", error));
  }, []);

  const handleEdit = (person) => {
    setEditingPerson(person);
  };

  const handleDelete = (personId) => {
    axios.delete(`http://localhost:8080/persons/${personId}`)
      .then(() => {
        setPersons(persons.filter(p => p.personId !== personId));
      })
      .catch(error => console.error("Error deleting person:", error));
  };

  const handleRegister = () => {
    const method = editingPerson.personId ? 'put' : 'post';
    const url = `http://localhost:8080/persons${editingPerson.personId ? `/${editingPerson.personId}` : ''}`;

    axios[method](url, editingPerson)
      .then(response => {
        if (method === 'post') {
          setPersons([...persons, response.data]);
        } else {
          setPersons(persons.map(p => p.personId === editingPerson.personId ? response.data : p));
        }
        setEditingPerson({ personId: '', personName: '', age: '', gender: '' });
      })
      .catch(error => console.error("Error saving person:", error));
  };

  return (
    <div>
      <div id="inputFormBlock">
        <label>ID: <input type="text" value={editingPerson.personId} onChange={(e) => setEditingPerson({...editingPerson, personId: e.target.value})} readOnly /></label>
        <label>名前: <input type="text" value={editingPerson.personName} onChange={(e) => setEditingPerson({...editingPerson, personName: e.target.value})} /></label>
        <label>年齢: <input type="text" value={editingPerson.age} onChange={(e) => setEditingPerson({...editingPerson, age: e.target.value})} /></label>
        <label>性別: <select value={editingPerson.gender} onChange={(e) => setEditingPerson({...editingPerson, gender: e.target.value})}>
          <option value=""></option>
          <option value="male">男性</option>
          <option value="female">女性</option>
        </select></label>
        <button onClick={handleRegister}>登録</button>
      </div>
      <hr />
      <div id="tableFormBlock">
        <PersonTable persons={persons} onEdit={handleEdit} onDelete={handleDelete} />
      </div>
    </div>
  );
};

export default App;
