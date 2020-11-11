export const api_base =
  process.env.NODE_ENV === "development" || process.env.NODE_ENV === "test"
    ? "http://localhost:8010/"
    : process.env.REACT_APP_API_BASE;
