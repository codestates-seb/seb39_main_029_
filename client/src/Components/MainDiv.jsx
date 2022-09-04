import styled from "styled-components";
import { useNavigate } from "react-router-dom";

function MainDiv() {
  const navigate = useNavigate();
  return (
    <Container>
      <Card bgcolor="var(--theme-ornage-light)">
        <div>아이콘</div>
        <CardContent>
          Find the best answer to your technical question, help others answer
          theirs
        </CardContent>
        <CardButton
          bgcolor="var(--theme-orange)"
          onClick={() => {
            navigate("/login");
          }}
        >
          Join the community
        </CardButton>
        <CardTag>or search content</CardTag>
      </Card>
      <Card bgcolor="var(--theme-blue-light)">
        <div>아이콘</div>
        <CardContent>
          Want a secure, private space for your technical knowledge?
        </CardContent>
        <CardButtonList>
          <CardButton bgcolor="var(--theme-blue)">
            For large organizations{" "}
          </CardButton>
          <CardButton bgcolor="var(--theme-blue)">For small teams </CardButton>
        </CardButtonList>
      </Card>
    </Container>
  );
}

const Container = styled.div`
  margin-top: 50px;
  width: 70%;
  height: 350px;
  display: flex;
  justify-content: space-between;
  gap: 1.5rem;
`;

const CardButtonList = styled.div`
  display: flex;
  width: 80%;
  gap: 0.5rem;
  button {
    height: 45px;
    &:first-child {
      width: 80%;
    }
  }
`;

const Card = styled.div`
  border-radius: 0.5rem;
  height: 250px;
  width: 50%;
  background-color: ${(props) => props.bgcolor};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-evenly;
`;

const CardContent = styled.div`
  text-align: center;
  font-size: 19px;
  width: 80%;
`;

const CardButton = styled.button`
  border-radius: 0.3rem;
  border: 0px solid;
  height: 50px;
  width: 200px;
  color: white;
  font-weight: 550;
  font-size: var(--text-font);
  background-color: ${(props) => props.bgcolor};
  cursor: pointer;
`;

const CardTag = styled.div`
  font-size: var(--normal-font);
  color: gray;
`;

export default MainDiv;
