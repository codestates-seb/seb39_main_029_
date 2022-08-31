import styled from "styled-components";
import "../index";

function DetailQnA() {
  return (
    <Wrapper>
      <TitleWrapper>
        <Title></Title>
        <CreatedAt></CreatedAt>
        <button></button>
      </TitleWrapper>
      <QWrapeer>
        <Vote></Vote>
        <Q></Q>
      </QWrapeer>
      <AWrapper>
        <Vote>
          <A></A>
        </Vote>
        <NewA></NewA>
      </AWrapper>
    </Wrapper>
  );
}

const Wrapper = styled.div``;

export default DetailQnA;
