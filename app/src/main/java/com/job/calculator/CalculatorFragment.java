package com.job.calculator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment for displaying calculator
 */

public class CalculatorFragment extends Fragment {
    private static final String TAG = "CalculatorFragment";
    private static final int MAX_DIGITS = 10;
    private static final char DIGIT_SEPARATOR = ',';
    private String currentNumber = "";
    private long result;
    private Operations operation;
    private boolean isThereResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        setClickListeners(view);


        return view;
    }

    public void setClickListeners(final View view) {
        final TextView textView = view.findViewById(R.id.textView);
        view.findViewById(R.id.zero_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS && (currentNumber.length() == 0 || Long.parseLong(currentNumber) != 0)) {
                    currentNumber += 0;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.one_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 1;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.two_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 2;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.three_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 3;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.four_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 4;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.five_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 5;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.six_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 6;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.seven_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 7;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.eight_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 8;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.nine_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentNumber.length() < MAX_DIGITS) {
                    currentNumber += 9;
                    updateTextView(textView);
                }
            }
        });

        view.findViewById(R.id.minus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = operation == null && isThereResult ? Operations.MINUS : operation;

                calculateResult(getCurrentNumberAsNumber());

                currentNumber = "";
                operation = Operations.MINUS;
                String newText = putComma(String.valueOf(result)) + "\n" + operation;
                textView.setText(newText);
            }
        });

        view.findViewById(R.id.plus_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = operation == null && isThereResult ? Operations.PLUS : operation;

                calculateResult(getCurrentNumberAsNumber());

                currentNumber = "";
                operation = Operations.PLUS;
                String newText = putComma(String.valueOf(result)) + "\n" + operation;
                textView.setText(newText);
            }
        });

        view.findViewById(R.id.multiply_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation = operation == null && isThereResult ? Operations.MULL : operation;

                calculateResult(getCurrentNumberAsNumber());

                currentNumber = "";
                operation = Operations.MULL;
                String newText = putComma(String.valueOf(result)) + "\n" + operation;
                textView.setText(newText);
            }
        });

        view.findViewById(R.id.equal_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long number = getCurrentNumberAsNumber();

                if(!"".equals(currentNumber)){
                    calculateResult(number);
                }

                if (operation == null && !" ".equals(getTextViewLastLine(textView))) {
                    result = number;
                }

                operation = null;
                currentNumber = "";
                String newText = putComma(String.valueOf(result)) + "\n ";
                textView.setText(newText);
            }
        });

    }

    private void calculateResult(long number) {
        if (operation != null) {
            switch (operation) {
                case MULL:
                    result *= number + ("".equals(currentNumber) ? 1 : 0);
                    break;
                case PLUS:
                    result += number;
                    break;
                case MINUS:
                    result -= number;
                    break;
                case DIVIDE:
                    result /= number;
                    break;
            }
        } else {
            result = number;
        }
        isThereResult = true;
    }

    private String getTextViewLastLine(TextView textView) {
        String lines[] = textView.getText().toString().split("\\r?\\n");
        return lines[lines.length - 1];
    }

    private void updateTextView(TextView textView) {
        String newText = getTextViewTextWithoutLastLine(textView) + (operation != null ? operation : "") + " " + putComma(currentNumber);
        textView.setText(newText);
    }

    private String getTextViewTextWithoutLastLine(TextView textView) {
        String[] text = textView.getText().toString().split("\\r?\\n");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length - 1; i++) {
            stringBuilder.append(text[i]).append("\n");
        }
        return stringBuilder.toString();
    }

    private StringBuilder putComma(String number) {
        StringBuilder textWithComma = new StringBuilder(number);
        int lastChar = '-' == (number.charAt(0)) ? 1 : 0;

        for (int i = number.length() - 3; i > lastChar; i -= 3) {
            textWithComma.insert(i, DIGIT_SEPARATOR);
        }

        return textWithComma;
    }

    public Long getCurrentNumberAsNumber() {
        return "".equals(currentNumber) ? 0 : Long.parseLong(currentNumber);
    }
}
