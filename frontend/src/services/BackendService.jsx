import axios from "axios";

const BackendService = axios.create({
  baseURL: "http://localhost:8081",
  headers: {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
});

export default BackendService;