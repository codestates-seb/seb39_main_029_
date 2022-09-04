import styled from "styled-components";
import { useEffect, useState } from "react";

function TextAnimation2() {
  const text = [
    "developer",
    "data scientist",
    "system admin",
    "mobile developer",
    "game developer",
  ];
  const [view, setView] = useState(text[0]);
  let count = -1;

  useEffect(() => {
    let timer = setInterval(() => {
      count = (count + 1) % 5;
      setView(text[count]);
    }, 1500);

    return () => clearInterval(timer);
  }, []);

  return (
    <>
      <Container>
        <Text>Every</Text>
        <div className="edit">{view}</div>
        <Text>has a</Text>
      </Container>
      <Text>tab open to Stack Overflow</Text>
    </>
  );
}

const Container = styled.div`
  display: flex;
  justify-content: center;
  .edit {
    position: relative;
    animation-duration: 1500ms;
    animation-name: down;
    animation-iteration-count: infinite;
    color: #f2740d;
    font-weight: bold;
    font-size: 55px;
    margin: 0px 5px;
  }

  @keyframes down {
    from {
      top: -50px;
    }
    to {
      top: 0px;
    }
  }
`;
const Text = styled.div`
  color: white;
  font-weight: bold;
  font-size: 55px;
  margin-top: 5px;
`;

export default TextAnimation2;
