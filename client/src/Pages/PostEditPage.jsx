import styled from "styled-components";
import Nav from "../Components/Nav";
import AskBody from "../Components/AskBody";
import ColorButton from "../Assets/ColorBtn";

function PostEditPage() {
  return (
    <Container>
      <Nav />
      <Wrapper>
        <AskBody />
      </Wrapper>
      <ButtonWrapper>
        <ColorButton mode="BLUE" text="Save Edit"></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: var(--theme-selected-grey);
  position: absolute;
  top: 0;
  left: 0;
  width: 100vmax;
  height: 100vmax;
`;

const Wrapper = styled.div`
  margin-top: 150px;
`;

const ButtonWrapper = styled.div`
  margin-top: 40px;
  width: 910px;
  display: flex;
`;
export default PostEditPage;
